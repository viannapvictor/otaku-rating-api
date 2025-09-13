package com.otaku.rating.core.user.model;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.properties.user.UserProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Getter;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Getter
public final class AccessToken {
    private final String code;
    private final Long userId;

    public AccessToken(
            RefreshToken refreshToken,
            UserProperties userProperties,
            SecretKey accessSecretKey,
            SecretKey refreshSecretKey
    ) throws ValidationException {
        if (userProperties == null) {
            throw new IllegalArgumentException("The user properties must not be null.");
        }
        if (accessSecretKey == null) {
            throw new IllegalArgumentException("The access secret key must not be null.");
        }
        if (refreshSecretKey == null) {
            throw new IllegalArgumentException("The refresh secret key must not be null.");
        }
        if (refreshToken == null) {
            throw new ValidationException("REFRESH_TOKEN_INVALID", "Refresh token is required.");
        }
        if (refreshToken.isExpired()) {
            throw new ValidationException("REFRESH_TOKEN_EXPIRED", "Refresh token has expired.");
        }
        Claims claims = refreshToken.getClaims(refreshSecretKey);
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusSeconds(userProperties.getAccessTokenExpirationSeconds());
        this.userId = refreshToken.getUserId();
        this.code = Jwts.builder()
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiration))
                .subject(refreshToken.getUserId().toString())
                .claim("userName", claims.get("userName"))
                .claim("name", claims.get("name"))
                .claim("email", claims.get("email"))
                .signWith(accessSecretKey)
                .compact();
    }

    public AccessToken(String token, SecretKey secretKey) throws ValidationException {
        if (secretKey == null) {
            throw new IllegalArgumentException("The secret key must not be null.");
        }
        if (token == null) {
            throw new ValidationException("ACCESS_TOKEN_INVALID", "Access token is required.");
        }
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            if (claims.getExpiration() == null) {
                throw new ValidationException("ACCESS_TOKEN_INVALID", "Access token expiration is required.");
            }
            validateCommonClaims(claims);
            this.code = token;
            this.userId = Long.parseLong(claims.getSubject());
        } catch (ExpiredJwtException e) {
            throw new ValidationException("ACCESS_TOKEN_EXPIRED", "Access token has expired.");
        } catch (JwtException e) {
            throw new ValidationException("ACCESS_TOKEN_INVALID", "Access token is invalid.");
        }
    }

    private AccessToken(String code, Long userId) {
        this.code = code;
        this.userId = userId;
    }

    private static void validateCommonClaims(Claims claims) throws ValidationException {
        try {
            Long.parseLong(claims.getSubject());
            if (claims.get("userName") == null ||
                    claims.get("name") == null ||
                    claims.get("email") == null ||
                    claims.getIssuedAt() == null) {
                throw new ValidationException("ACCESS_TOKEN_INVALID", "Access token is missing required claims.");
            }
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new ValidationException("ACCESS_TOKEN_INVALID", "Access token format is invalid.");
        }
    }

    public static AccessToken parseUnsafe(String code, Long userId) {
        return new AccessToken(code, userId);
    }
}
