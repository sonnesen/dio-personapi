package one.digitalinnovation.personapi.security.services;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final RefreshTokenService refreshTokenService;

    public void logout() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refreshTokenService.deleteByUserId(principal.getId());
    }
}
