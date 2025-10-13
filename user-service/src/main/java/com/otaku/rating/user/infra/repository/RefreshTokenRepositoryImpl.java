package com.otaku.rating.user.infra.repository;

import com.otaku.rating.user.core.model.RefreshToken;
import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.repository.RefreshTokenRepository;
import com.otaku.rating.user.infra.mapper.RefreshTokenMapper;
import com.otaku.rating.user.infra.persistence.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    
    private final RefreshTokenEntityRepository refreshTokenEntityRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public void deleteAllByUser(User user) {
        refreshTokenEntityRepository.deleteAllByUserById(user.getId());
    }

    @Override
    public boolean existsByCode(String code) {
        return refreshTokenEntityRepository.existsById(code);
    }

    @Override
    public Optional<RefreshToken> findByCode(String code) {
        return refreshTokenEntityRepository.findById(code)
                .map(refreshTokenMapper::toModel);
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenMapper.toEntity(refreshToken);
        RefreshTokenEntity savedEntity = refreshTokenEntityRepository.save(refreshTokenEntity);
        return refreshTokenMapper.toModel(savedEntity);
    }

    @Override
    public void deleteByCode(String code) {
        refreshTokenEntityRepository.deleteById(code);
    }
}