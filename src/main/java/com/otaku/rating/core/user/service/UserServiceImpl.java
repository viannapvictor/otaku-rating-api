package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.exception.*;
import com.otaku.rating.core.user.model.*;
import com.otaku.rating.core.user.model.properties.user.UserProperties;
import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.model.valueobject.Name;
import com.otaku.rating.core.user.model.valueobject.Password;
import com.otaku.rating.core.user.model.valueobject.UserName;
import com.otaku.rating.core.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Getter
    private final ContextService context;
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final EmailConfirmationService emailConfirmationService;
    private final TokenService tokenService;
    private final PasswordResetService passwordResetService;
    private final UserProperties userProperties;

    @Override
    @Transactional
    public User createUser(UserRegister userRegister) {
        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        if (userRepository.existsByUserName(userRegister.getUserName())) {
            throw new UserNameAlreadyExistsException();
        }
        
        User user = new User(passwordEncoderService, userRegister);
        User savedUser = userRepository.save(user);

        emailConfirmationService.addEmailConfirmation(savedUser, null);
        return User.parseUnsafe(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getUserName(),
                savedUser.getName(),
                savedUser.getEncryptedPassword(),
                savedUser.getRole(),
                false,
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }

    @Override
    public Page<User> getPage(int page, int size) {
        return userRepository.findAll(page, size);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByEmail(Email email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByUserName(UserName userName) {
        return userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public AuthTokens login(Email email, Password password) {
        if (context.isAuthenticated()) {
            throw new AlreadyAuthenticatedException();
        }
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !passwordEncoderService.checkPassword(user, password)) {
            throw new LoginWrongCredentialsException();
        }
        if (!user.isActive()) {
            throw new EmailConfirmationPendingException();
        }
        RefreshToken refreshToken = tokenService.createRefreshToken(user);
        AccessToken accessToken = tokenService.createAccessToken(refreshToken);

        return new AuthTokens(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public void resetPassword(String code, Password newPassword) {
        PasswordReset passwordReset = passwordResetService.resetPassword(code);
        Optional<User> optionalUser = userRepository.findById(passwordReset.getUserId());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        user.setPassword(passwordEncoderService, newPassword);

        userRepository.save(user);
    }

    @Override
    public void logout() {
        String refreshToken = context.getCookieValueOrDefault(
                userProperties.getRefreshTokenCookieName(),
                null
        );
        if (!context.isAuthenticated() || refreshToken == null) return;

        tokenService.deleteRefreshToken(refreshToken);
    }

    @Override
    @Transactional
    public void confirmEmail(String code, Email currentEmail) {
        User user = null;
        if (currentEmail == null) {
            user = context.getUserOrThrow();
            currentEmail = user.getEmail();
        }
        EmailConfirmation emailConfirmation = emailConfirmationService.confirmEmail(code, currentEmail);
        if (emailConfirmation.getNewEmail() == null) return;
        if (user == null) {
            user = userRepository.findById(emailConfirmation.getUserId()).orElseThrow(UserNotFoundException::new);
        }
        user.setEmail(emailConfirmation.getNewEmail());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUserProfile(Name name, UserName userName) {
        User user = context.getUserOrThrow();
        if (name != null) {
            user.setName(name);
        }
        if (userName != null) {
            if (!user.getUserName().equals(userName) && userRepository.existsByUserName(userName)) {
                throw new UserNameAlreadyExistsException();
            }
            user.setUserName(userName);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserPassword(Password oldPassword, Password newPassword) {
        User user = context.getUserOrThrow();
        if (oldPassword == null || newPassword == null) {
            throw new IllegalArgumentException("Cannot pass a null password object.");
        }
        if (!passwordEncoderService.checkPassword(user, oldPassword)) {
            throw new OldPasswordWrongException();
        }
        user.setPassword(passwordEncoderService, newPassword);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserEmail(Email email) {
        User user = context.getUserOrThrow();
        if (email == null) {
            throw new IllegalArgumentException("Cannot pass a null email object");
        }
        if (user.getEmail().equals(email)) {
            return;
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
        emailConfirmationService.addEmailConfirmation(user, email);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        tokenService.revokeAllRefreshTokens(user);
        passwordResetService.deleteFromUser(user);
        emailConfirmationService.deleteFromUser(user);
        userRepository.delete(user);
    }
}