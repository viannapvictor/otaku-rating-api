package com.otaku.rating.infra.user.repository;

import com.otaku.rating.infra.user.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserName(String userName);
}