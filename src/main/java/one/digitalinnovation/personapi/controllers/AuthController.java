package one.digitalinnovation.personapi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dtos.*;
import one.digitalinnovation.personapi.security.services.AuthenticationService;
import one.digitalinnovation.personapi.security.services.LogoutService;
import one.digitalinnovation.personapi.security.services.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final LogoutService logoutService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(refreshTokenService.refreshToken(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        logoutService.logout();
        return ResponseEntity.noContent().build();
    }
}
