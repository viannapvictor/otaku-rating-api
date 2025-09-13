package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.EmailConfirmationRequestDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.EmailConfirmationRequest;
import com.otaku.rating.core.user.model.valueobject.Email;
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