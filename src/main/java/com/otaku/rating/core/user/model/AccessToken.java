package com.otaku.rating.core.user.model;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.exception.AccessTokenExpiredException;
import com.otaku.rating.core.user.exception.AccessTokenInvalidException;
import com.otaku.rating.core.user.exception.RefreshTokenExpiredException;
import com.otaku.rating.core.user.exception.RefreshTokenInvalidException;
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
    ) {
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
            throw new RefreshTokenInvalidException();
        }
        if (refreshToken.isExpired()) {
            throw new RefreshTokenExpiredException();
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

    public AccessToken(String token, SecretKey secretKey) {
        if (secretKey == null) {
            throw new IllegalArgumentException("The secret key must not be null.");
        }
        if (token == null) {
            throw new AccessTokenInvalidException();
        }
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            if (claims.getExpiration() == null) {
                throw new AccessTokenInvalidException();
            }
            validateCommonClaims(claims);
            this.code = token;
            this.userId = Long.parseLong(claims.getSubject());
        } catch (ExpiredJwtException e) {
            throw new AccessTokenExpiredException();
        } catch (JwtException e) {
            throw new AccessTokenInvalidException();
        }
    }

    private AccessToken(String code, Long userId) {
        this.code = code;
        this.userId = userId;
    }

    private static void validateCommonClaims(Claims claims) {
        try {
            Long.parseLong(claims.getSubject());
            if (claims.get("userName") == null ||
                    claims.get("name") == null ||
                    claims.get("email") == null ||
                    claims.getIssuedAt() == null) {
                throw new AccessTokenInvalidException();
            }
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new AccessTokenInvalidException();
        }
    }

    public static AccessToken parseUnsafe(String code, Long userId) {
        return new AccessToken(code, userId);
    }
}
