package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.AccessToken;
import com.otaku.rating.core.user.model.RefreshToken;
import com.otaku.rating.core.user.model.User;

public interface TokenService {
    AccessToken createAccessToken(RefreshToken refreshToken);
    AccessToken retrieveAccessToken(String accessToken);
    RefreshToken createRefreshToken(User user);
    RefreshToken increaseRefreshTokenExpiration(String refreshToken);
    void deleteRefreshToken(String token);
    void revokeAllRefreshTokens(User user);
}