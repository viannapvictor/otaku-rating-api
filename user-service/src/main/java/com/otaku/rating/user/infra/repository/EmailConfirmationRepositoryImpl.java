package com.otaku.rating.user.infra.repository;

import com.otaku.rating.user.core.model.EmailConfirmation;
import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.model.valueobject.Email;
import com.otaku.rating.user.core.repository.EmailConfirmationRepository;
import com.otaku.rating.user.infra.mapper.EmailConfirmationMapper;
import com.otaku.rating.user.infra.persistence.EmailConfirmationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailConfirmationRepositoryImpl implements EmailConfirmationRepository {
    
    private final EmailConfirmationEntityRepository emailConfirmationEntityRepository;
    private final EmailConfirmationMapper emailConfirmationMapper;

    @Override
    public Optional<EmailConfirmation> findByUserEmail(Email email) {
        return emailConfirmationEntityRepository.findByUserEmail(email.getValue())
                .map(emailConfirmationMapper::toModel);
    }

    @Override
    public boolean existsByUserEmail(Email email) {
        return emailConfirmationEntityRepository.existsByUserEmail(email.getValue());
    }

    @Override
    public List<EmailConfirmation> findAllExpired(Instant now) {
        return emailConfirmationEntityRepository.findAllExpired(now)
                .stream()
                .map(emailConfirmationMapper::toModel)
                .toList();
    }

    @Override
    public void delete(EmailConfirmation emailConfirmation) {
        emailConfirmationEntityRepository.deleteById(emailConfirmation.getCode().getValue());
    }

    @Override
    public void deleteFromUser(User user) {
        emailConfirmationEntityRepository.deleteByUserId(user.getId());
    }

    @Override
    public EmailConfirmation save(EmailConfirmation emailConfirmation) {
        EmailConfirmationEntity emailConfirmationEntity = emailConfirmationMapper.toEntity(emailConfirmation);
        EmailConfirmationEntity savedEntity = emailConfirmationEntityRepository.save(emailConfirmationEntity);
        return emailConfirmationMapper.toModel(savedEntity);
    }
}