package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.PasswordReset;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.properties.user.UserProperties;
import com.otaku.rating.core.user.repository.PasswordResetRepository;
import com.otaku.rating.core.user.service.messaging.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages");

    private final PasswordResetRepository passwordResetRepository;
    private final UserProperties userProperties;
    private final EmailSender emailSender;

    @Override
    @Transactional
    public PasswordReset createPasswordResetRequest(User user) throws ValidationException {
        Optional<PasswordReset> active = passwordResetRepository.findByUser(user);
        if (active.isPresent()) {
            if (!active.get().isExpired()) throw new ValidationException("PASSWORD_RESET_REQUEST_EXISTS", "Password reset request already exists");
            passwordResetRepository.delete(active.get());
        }
        PasswordReset passwordReset = new PasswordReset(user, userProperties);
        PasswordReset savedPasswordReset = passwordResetRepository.save(passwordReset);

        String subject = messages.getString("message.password.reset.subject");
        String body = String.format(
                messages.getString("message.password.reset.body"),
                user.getName().getValue(),
                savedPasswordReset.getCode().encodeToUrlCode(),
                userProperties.getResetPasswordConfirmationExpirationMinutes()
        );
        emailSender.sendEmail(user, subject, body);
        return savedPasswordReset;
    }

    @Override
    @Transactional
    public PasswordReset resetPassword(String code) throws ValidationException {
        Optional<PasswordReset> passwordResetOptional = passwordResetRepository.findByCode(code);
        if (passwordResetOptional.isEmpty() || passwordResetOptional.get().isExpired()) {
            throw new ValidationException("PASSWORD_RESET_CODE_INVALID", "Password reset code is invalid");
        }
        PasswordReset passwordReset = passwordResetOptional.get();
        passwordResetRepository.delete(passwordReset);

        return passwordReset;
    }

    @Override
    public void deleteFromUser(User user) {
        passwordResetRepository.deleteFromUser(user);
    }
}
