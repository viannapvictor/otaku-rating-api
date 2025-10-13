package com.otakurating.user.core.service;

import com.otakurating.user.app.response.dto.TokenResponseDTO;

public interface KeycloakAuthService {
    TokenResponseDTO login(String usernameOrEmail, String password);
    TokenResponseDTO refreshToken(String refreshToken);
    void logout(String refreshToken);
    void sendPasswordResetEmail(String email);
    void sendEmailVerification(String userId);
}