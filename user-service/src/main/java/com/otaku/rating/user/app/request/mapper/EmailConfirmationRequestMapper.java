package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.EmailConfirmationRequestDTO;
import com.otaku.rating.user.core.model.EmailConfirmationRequest;
import com.otaku.rating.user.core.model.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConfirmationRequestMapper implements InputMapper<EmailConfirmationRequest, EmailConfirmationRequestDTO> {
    @Override
    public EmailConfirmationRequest toModel(EmailConfirmationRequestDTO emailConfirmationRequestDTO) {
        Email email = emailConfirmationRequestDTO.getEmail() != null
                ? Email.valueOf(emailConfirmationRequestDTO.getEmail())
                : null;
        return new EmailConfirmationRequest(
                emailConfirmationRequestDTO.getCode(),
                email
        );
    }
}