package com.otaku.rating.core.user.repository;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.UserName;
import com.otaku.rating.infra.user.entities.UserEntity;
import com.otaku.rating.infra.user.mapper.UserMapper;
import com.otaku.rating.infra.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public boolean existsByEmail(Email email) {
        return userJpaRepository.existsByEmail(email.getValue());
    }

    @Override
    public boolean existsByUserName(UserName userName) {
        return userJpaRepository.existsByUserName(userName.getValue());
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userJpaRepository.findByEmail(email.getValue())
                .map(userMapper::toModel);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(userMapper::toModel);
    }

    @Override
    public Optional<User> findByUserName(UserName userName) {
        return userJpaRepository.findByUserName(userName.getValue())
                .map(userMapper::toModel);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return userMapper.toModel(savedEntity);
    }

    @Override
    public Page<User> findAll(int page, int size) {
        org.springframework.data.domain.PageRequest pageRequest = org.springframework.data.domain.PageRequest.of(page, size);
        return userJpaRepository.findAll(pageRequest).map(userMapper::toModel);
    }

    @Override
    public void delete(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userJpaRepository.delete(userEntity);
    }
}
