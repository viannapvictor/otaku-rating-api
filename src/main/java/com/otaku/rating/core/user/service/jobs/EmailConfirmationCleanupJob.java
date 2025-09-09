package com.otaku.rating.core.user.service.jobs;

import com.otaku.rating.core.generic.exception.NotFoundException;
import com.otaku.rating.core.user.model.EmailConfirmation;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.service.EmailConfirmationService;
import com.otaku.rating.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailConfirmationCleanupJob {
    private final EmailConfirmationService emailConfirmationService;
    private final UserService userService;

    @Scheduled(cron = "${app.user.email-confirmation-manager-job}")
    @Transactional
    public void manageExpiredEmailConfirmations() throws NotFoundException {
        List<EmailConfirmation> usersExpired = emailConfirmationService.findExpiredAccounts();
        for (EmailConfirmation emailConfirmation : usersExpired) {
            if (emailConfirmation.getNewEmail() == null) {
                User user = userService.findById(emailConfirmation.getUserId());
                userService.deleteUser(user);
                continue;
            }
            emailConfirmationService.delete(emailConfirmation);
        }
    }
}
