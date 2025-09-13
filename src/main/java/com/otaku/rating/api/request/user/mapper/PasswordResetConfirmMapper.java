package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.PasswordResetConfirmDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.PasswordResetConfirm;
import com.otaku.rating.core.user.model.valueobject.Password;
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