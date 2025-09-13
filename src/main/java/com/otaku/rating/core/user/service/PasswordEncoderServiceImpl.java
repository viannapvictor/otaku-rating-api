package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobject.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordEncoderServiceImpl implements PasswordEncoderService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryptPassword(Password password) {
        return passwordEncoder.encode(password.getValue());
    }

    @Override
    public boolean checkPassword(User user, Password givenPassword) {
        return passwordEncoder.matches(givenPassword.getValue(), user.getEncryptedPassword());
    }
}