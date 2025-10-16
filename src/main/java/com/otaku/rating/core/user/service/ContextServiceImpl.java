package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.exception.LoginWrongCredentialsException;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.repository.UserRepository;
import com.otaku.rating.core.user.model.valueobject.Email;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContextServiceImpl implements ContextService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserOptional() {
        String principalEmail = getPrincipalName();
        if (principalEmail == null) {
            return Optional.empty();
        }
        return userRepository.findByEmail(Email.valueOf(principalEmail));
    }

    @Override
    @NonNull
    public User getUserOrThrow() {
        return getUserOptional()
                .orElseThrow(LoginWrongCredentialsException::new);
    }

    @Override
    public String getPrincipalName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String email = jwt.getClaimAsString("email");
            if (email != null) {
                return email;
            }
            String preferredUsername = jwt.getClaimAsString("preferred_username");
            if (preferredUsername != null) {
                return preferredUsername;
            }
            return jwt.getSubject();
        }

        return authentication.getName();
    }
}