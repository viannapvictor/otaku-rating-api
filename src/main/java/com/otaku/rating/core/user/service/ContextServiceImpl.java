package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.AccessToken;
import com.otaku.rating.core.user.model.AuthTokens;
import com.otaku.rating.core.user.model.RefreshToken;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.repository.UserRepository;
import com.otaku.rating.core.user.service.decorators.NeedsUserContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ContextServiceImpl implements ContextService {
    private final TokenService tokenService;
    private final Map<String, String> cookies;
    private final User user;

    public ContextServiceImpl(
        UserRepository userRepository,
        TokenService tokenService,
        HttpServletRequest request
    ) {
        this.tokenService = tokenService;
        this.cookies = new HashMap<>();

        HandlerMethod handlerMethod = (HandlerMethod)request.getAttribute(
                HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE
        );
        boolean hasMethodAnnotation = handlerMethod.getMethod().isAnnotationPresent(NeedsUserContext.class);
        boolean hasClassAnnotation = handlerMethod.getBeanType().isAnnotationPresent(NeedsUserContext.class);

        if ((!hasMethodAnnotation && !hasClassAnnotation) || request.getCookies() == null) {
            this.user = null;
            return;
        }

        for (Cookie cookie : request.getCookies()) {
            this.cookies.put(cookie.getName(), cookie.getValue());
        }

        String accessToken = cookies.getOrDefault("access_token", null);
        if (accessToken == null) {
            this.user = null;
            return;
        }

        long id;
        try {
            id = tokenService.retrieveAccessToken(accessToken).getUserId();
        } catch (ValidationException e) {
            this.user = null;
            return;
        }
        this.user = userRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<User> getUserOptional() {
        return Optional.ofNullable(user);
    }

    @Override
    @NonNull
    public User getUserOrThrow() throws ValidationException {
        if (user == null) {
            throw new ValidationException("COMMON_UNAUTHORIZED", "Unauthorized access");
        }
        return user;
    }

    @Override
    public AuthTokens refreshTokens() throws ValidationException {
        String refreshToken = getCookieValue("refresh_token");
        if (refreshToken == null) {
            throw new ValidationException("REFRESH_TOKEN_EXPIRED", "Refresh token expired");
        }
        RefreshToken updatedRefreshToken = tokenService.increaseRefreshTokenExpiration(refreshToken);
        AccessToken newAccessToken = tokenService.createAccessToken(updatedRefreshToken);

        return new AuthTokens(newAccessToken, updatedRefreshToken);
    }

    @Override
    public void throwIfNotAuthenticated() throws ValidationException {
        if (!isAuthenticated()) {
            throw new ValidationException("COMMON_UNAUTHORIZED", "Unauthorized access");
        }
    }

    @Override
    public boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public String getCookieValue(String key) {
        return cookies.get(key);
    }

    @Override
    public String getCookieValueOrDefault(String key, String defaultValue) {
        return cookies.getOrDefault(key, defaultValue);
    }
}