package com.otaku.rating.user.core.job;

import com.otaku.rating.user.core.model.EmailConfirmation;
import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.service.EmailConfirmationService;
import com.otaku.rating.user.core.service.UserService;
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
    public void manageExpiredEmailConfirmations() {
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
