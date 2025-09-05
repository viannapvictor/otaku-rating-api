package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.AccessToken;
import com.otaku.rating.core.user.model.RefreshToken;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.properties.user.UserProperties;
import com.otaku.rating.core.user.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
public class TokenServiceImpl implements TokenService {
    private static final SecretKey accessSecretKey = Jwts.SIG.HS256.key().build();
    private final SecretKey refreshSecretKey;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserProperties userProperties;

    public TokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserProperties userProperties) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userProperties = userProperties;

        byte[] keyBytes = userProperties.getRefreshTokenSecret().getBytes(StandardCharsets.UTF_8);
        refreshSecretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    @Override
    public AccessToken createAccessToken(RefreshToken refreshToken) throws ValidationException {
        return new AccessToken(refreshToken, userProperties, accessSecretKey, refreshSecretKey);
    }

    @Override
    public AccessToken retrieveAccessToken(String accessToken) throws ValidationException {
        return new AccessToken(accessToken, accessSecretKey);
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken(user, userProperties, refreshSecretKey);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken increaseRefreshTokenExpiration(String refreshToken) throws ValidationException {
        RefreshToken refreshTokenFromDatabase = refreshTokenRepository.findByCode(refreshToken).orElse(null);
        if (refreshTokenFromDatabase == null) {
            throw new ValidationException("REFRESH_TOKEN_EXPIRED", "Refresh token expired");
        }
        refreshTokenFromDatabase.increaseExpiration(userProperties);
        return refreshTokenRepository.save(refreshTokenFromDatabase);
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByCode(token);
    }

    @Override
    public void revokeAllRefreshTokens(User user) {
        refreshTokenRepository.deleteAllByUser(user);
    }
}