package com.otaku.rating.core.user.repository;

import com.otaku.rating.core.user.model.EmailConfirmation;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobjects.Email;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface EmailConfirmationRepository {
    Optional<EmailConfirmation> findByUserEmail(Email email);
    boolean existsByUserEmail(Email email);
    List<EmailConfirmation> findAllExpired(Instant now);
    void delete(EmailConfirmation emailConfirmation);
    void deleteFromUser(User user);
    EmailConfirmation save(EmailConfirmation emailConfirmation);
}
