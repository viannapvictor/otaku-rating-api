package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.user.dto.*;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.auth.dto.TokenResponseDTO;
import com.otaku.rating.core.user.service.KeycloakAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final KeycloakAuthService keycloakAuthService;

    @Value("${app.user.cookie-secure:false}")
    private boolean cookieSecure;

    @Value("${app.user.cookie-http-only:true}")
    private boolean cookieHttpOnly;

    @Value("${app.user.access-token-cookie-name:access_token}")
    private String accessTokenCookieName;

    @Value("${app.user.refresh-token-cookie-name:refresh_token}")
    private String refreshTokenCookieName;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLoginDTO loginDTO, HttpServletResponse response) {
        try {
            TokenResponseDTO tokens = keycloakAuthService.login(loginDTO.getUsername(), loginDTO.getPassword());

            Cookie accessTokenCookie = createCookie(accessTokenCookieName, tokens.getAccessToken(), tokens.getExpiresIn());
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = createCookie(refreshTokenCookieName, tokens.getRefreshToken(), tokens.getRefreshExpiresIn());
            response.addCookie(refreshTokenCookie);

            return ApiResponse.success("Login successful").createResponse(HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponse.<String>error("LOGIN_FAILED", "Invalid credentials or user not found")
                    .createResponse(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refreshToken(@CookieValue(name = "refresh_token", required = false) String refreshToken,
                                                              HttpServletResponse response) {
        try {
            if (refreshToken == null) {
                return ApiResponse.<String>error("REFRESH_FAILED", "No refresh token provided in cookie")
                        .createResponse(HttpStatus.BAD_REQUEST);
            }

            TokenResponseDTO tokens = keycloakAuthService.refreshToken(refreshToken);

            Cookie accessTokenCookie = createCookie(accessTokenCookieName, tokens.getAccessToken(), tokens.getExpiresIn());
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = createCookie(refreshTokenCookieName, tokens.getRefreshToken(), tokens.getRefreshExpiresIn());
            response.addCookie(refreshTokenCookie);

            return ApiResponse.success("Token refreshed successfully").createResponse(HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponse.<String>error("REFRESH_FAILED", "Invalid or expired refresh token")
                    .createResponse(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Object>> logout(@CookieValue(name = "refresh_token", required = false) String refreshToken,
                                                       HttpServletResponse response) {
        try {
            if (refreshToken != null) {
                keycloakAuthService.logout(refreshToken);
            }

            Cookie accessTokenCookie = createCookie(accessTokenCookieName, "", 0);
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = createCookie(refreshTokenCookieName, "", 0);
            response.addCookie(refreshTokenCookie);

            return ApiResponse.success(null).createResponse(HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponse.error("LOGOUT_FAILED", "Failed to logout")
                    .createResponse(HttpStatus.BAD_REQUEST);
        }
    }

    private Cookie createCookie(String name, String value, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(cookieHttpOnly);
        cookie.setSecure(cookieSecure);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge != null ? maxAge : -1);
        return cookie;
    }
}