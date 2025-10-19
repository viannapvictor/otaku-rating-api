package com.otaku.rating.core.user.service;

import com.otaku.rating.api.response.auth.dto.TokenResponseDTO;

public interface KeycloakAuthService {

    TokenResponseDTO login(String usernameOrEmail, String password);

    TokenResponseDTO refreshToken(String refreshToken);

    void logout(String refreshToken);

    void sendPasswordResetEmail(String email);

    void sendEmailVerification(String userId);
}