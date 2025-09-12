package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.NotFoundException;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.*;
import com.otaku.rating.core.user.model.properties.user.UserProperties;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.Name;
import com.otaku.rating.core.user.model.valueobjects.Password;
import com.otaku.rating.core.user.model.valueobjects.UserName;
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
    private final PasswordEncoderServiceImpl passwordEncoderService;
    private final EmailConfirmationService emailConfirmationService;
    private final TokenService tokenService;
    private final PasswordResetService  passwordResetService;
    private final UserProperties userProperties;

    @Override
    @Transactional
    public User createUser(UserRegister userRegister) throws ValidationException {
        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new ValidationException("email.already.exists", "Email already exists");
        }
        if (userRepository.existsByUserName(userRegister.getUserName())) {
            throw new ValidationException("username.already.exists", "Username already exists");
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
    public User findById(long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user.not.exists", "There's no user for this id."));
    }

    @Override
    public User findByEmail(Email email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("user.not.exists", "There's no user for this email."));
    }

    @Override
    public User findByUserName(UserName userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new NotFoundException("user.not.exists", "There's no user for this username."));
    }

    @Override
    public AuthTokens login(Email email, Password password) throws ValidationException {
        if (context.isAuthenticated()) {
            throw new ValidationException("user.already.logged", "User already logged");
        }
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !passwordEncoderService.checkPassword(user, password)) {
            throw new ValidationException("user.unauthorized", "User not authorized.");
        }
        if (!user.isActive()) {
            throw new ValidationException("user.email.pending.validation", "Email validation is pending.");
        }
        RefreshToken refreshToken = tokenService.createRefreshToken(user);
        AccessToken accessToken = tokenService.createAccessToken(refreshToken);

        return new AuthTokens(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public void resetPassword(String code, Password newPassword) throws NotFoundException {
        PasswordReset passwordReset = passwordResetService.resetPassword(code);
        Optional<User> optionalUser = userRepository.findById(passwordReset.getUserId());
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("user.not.exists", "There's no user for this id.");
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
    public void confirmEmail(String code, Email currentEmail) throws NotFoundException {
        User user = null;
        if (currentEmail == null) {
            user = context.getUserOrThrow();
            currentEmail = user.getEmail();
        }
        EmailConfirmation emailConfirmation = emailConfirmationService.confirmEmail(code, currentEmail);
        if (emailConfirmation.getNewEmail() == null) return;
        if (user == null) {
            user = userRepository.findById(emailConfirmation.getUserId())
                    .orElseThrow(() -> new NotFoundException("user.not.exists", "There's no user for this id."));
        }
        user.setEmail(emailConfirmation.getNewEmail());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUserProfile(Name name, UserName userName) throws NotFoundException{
        User user = context.getUserOrThrow();
        if (name != null) {
            user.setName(name);
        }
        if (userName != null) {
            if (!user.getUserName().equals(userName) && userRepository.existsByUserName(userName)) {
                throw new NotFoundException("user.not.exists", "There's no user for this email.");
            }
            user.setUserName(userName);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserPassword(Password oldPassword, Password newPassword) throws ValidationException {
        User user = context.getUserOrThrow();
        if (oldPassword == null || newPassword == null) {
            throw new ValidationException("password.cannot.null", "Password cannot be null.");
        }
        if (!passwordEncoderService.checkPassword(user, oldPassword)) {
            throw new ValidationException("invalid.old.password", "The old password not match.");
        }
        user.setPassword(passwordEncoderService, newPassword);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserEmail(Email email) throws ValidationException {
        User user = context.getUserOrThrow();

        if (email == null) {
            throw new ValidationException("email.cannot.null", "Email cannot be null.");
        }
        if (user.getEmail().equals(email)) {
            return;
        }
        if (userRepository.existsByEmail(email)) {
            throw new ValidationException("username.already.exists", "Username already exists");
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