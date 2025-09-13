package com.otaku.rating.infra.user.repository;

import com.otaku.rating.core.user.model.PasswordReset;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.repository.PasswordResetRepository;
import com.otaku.rating.infra.user.persistence.PasswordResetEntity;
import com.otaku.rating.infra.user.persistence.UserEntity;
import com.otaku.rating.infra.user.mapper.PasswordResetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PasswordResetRepositoryImpl implements PasswordResetRepository {
    
    private final PasswordResetEntityRepository passwordResetEntityRepository;
    private final PasswordResetMapper passwordResetMapper;

    @Override
    public Optional<PasswordReset> findByCode(String code) {
        return passwordResetEntityRepository.findById(code)
                .map(passwordResetMapper::toModel);
    }

    @Override
    public Optional<PasswordReset> findByUser(User user) {
        UserEntity userEntity = UserEntity.builder().id(user.getId()).build();
        return  passwordResetEntityRepository.findByUser(userEntity)
                .map(passwordResetMapper::toModel);
    }

    @Override
    public void delete(PasswordReset passwordReset) {
        passwordResetEntityRepository.deleteById(passwordReset.getCode().getValue());
    }

    @Override
    public void deleteFromUser(User user) {
        passwordResetEntityRepository.deleteByUserId(user.getId());
    }

    @Override
    public PasswordReset save(PasswordReset passwordReset) {
        PasswordResetEntity passwordResetEntity = passwordResetMapper.toEntity(passwordReset);
        PasswordResetEntity savedEntity = passwordResetEntityRepository.save(passwordResetEntity);
        return passwordResetMapper.toModel(savedEntity);
    }
}