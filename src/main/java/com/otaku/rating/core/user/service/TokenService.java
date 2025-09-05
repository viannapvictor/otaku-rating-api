package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.AccessToken;
import com.otaku.rating.core.user.model.RefreshToken;
import com.otaku.rating.core.user.model.User;

public interface TokenService {
    AccessToken createAccessToken(RefreshToken refreshToken) throws ValidationException;
    AccessToken retrieveAccessToken(String accessToken) throws ValidationException;
    RefreshToken createRefreshToken(User user);
    RefreshToken increaseRefreshTokenExpiration(String refreshToken) throws ValidationException;
    void deleteRefreshToken(String token);
    void revokeAllRefreshTokens(User user);
}