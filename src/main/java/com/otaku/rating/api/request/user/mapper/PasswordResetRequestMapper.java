package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.PasswordResetRequestDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.valueobject.Email;
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