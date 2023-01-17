package one.digitalinnovation.personapi.security.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.digitalinnovation.personapi.dtos.RefreshTokenRequest;
import one.digitalinnovation.personapi.dtos.RefreshTokenResponse;
import one.digitalinnovation.personapi.exceptions.RefreshTokenException;
import one.digitalinnovation.personapi.exceptions.RefreshTokenExpirationException;
import one.digitalinnovation.personapi.models.RefreshToken;
import one.digitalinnovation.personapi.repositories.RefreshTokenRepository;
import one.digitalinnovation.personapi.repositories.UserRepository;
import one.digitalinnovation.personapi.security.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtHelper jwtHelper;
    @Value("${security.jwt.refresh_token_ms}")
    private Long refreshTokenInMillis;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    public RefreshToken createRefreshToken(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> {
            log.error("User with ID {} not found!", userId);
            return new RefreshTokenException();
        });

        var refreshToken = RefreshToken.builder()
                .user(user)
                .expireDate(Instant.now().plusMillis(refreshTokenInMillis))
                .token(UUID.randomUUID().toString())
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenExpirationException(token.getToken(),
                    "Refresh token was expired. Please make a new signIn request.");
        }
        return token;
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        return refreshTokenRepository.findByToken(refreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtHelper.generateTokenFromUsername(user.getUsername());
                    return RefreshTokenResponse.builder()
                            .accessToken(token)
                            .refreshToken(refreshToken)
                            .build();
                })
                .orElseThrow(() -> new RefreshTokenExpirationException(refreshToken, "Refresh token not found!"));
    }

    public void deleteByUserId(UUID id) {
        userRepository.findById(id).ifPresent(user -> refreshTokenRepository.deleteById(user.getId()));
    }
}
