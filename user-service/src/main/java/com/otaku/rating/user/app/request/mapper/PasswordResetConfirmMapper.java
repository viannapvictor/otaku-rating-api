package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.PasswordResetConfirmDTO;
import com.otaku.rating.user.core.model.PasswordResetConfirm;
import com.otaku.rating.user.core.model.valueobject.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetConfirmMapper implements InputMapper<PasswordResetConfirm, PasswordResetConfirmDTO> {
    @Override
    public PasswordResetConfirm toModel(PasswordResetConfirmDTO passwordResetConfirmDTO) {
        return new PasswordResetConfirm(
                passwordResetConfirmDTO.getCode(),
                Password.valueOf(passwordResetConfirmDTO.getNewPassword())
        );
    }
}