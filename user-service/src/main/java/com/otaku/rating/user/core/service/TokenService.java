package com.otaku.rating.user.core.service;

import com.otaku.rating.user.core.model.AccessToken;
import com.otaku.rating.user.core.model.RefreshToken;
import com.otaku.rating.user.core.model.User;

public interface TokenService {
    AccessToken createAccessToken(RefreshToken refreshToken);
    AccessToken retrieveAccessToken(String accessToken);
    RefreshToken createRefreshToken(User user);
    RefreshToken increaseRefreshTokenExpiration(String refreshToken);
    void deleteRefreshToken(String token);
    void revokeAllRefreshTokens(User user);
}