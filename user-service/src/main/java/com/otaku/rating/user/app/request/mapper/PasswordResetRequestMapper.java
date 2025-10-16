package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.PasswordResetRequestDTO;
import com.otaku.rating.user.core.model.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetRequestMapper implements InputMapper<Email, PasswordResetRequestDTO> {
    @Override
    public Email toModel(PasswordResetRequestDTO passwordResetRequestDTO) {
        return Email.valueOf(passwordResetRequestDTO.getEmail());
    }
}