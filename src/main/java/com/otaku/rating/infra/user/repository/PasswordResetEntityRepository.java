package com.otaku.rating.infra.user.repository;

import com.otaku.rating.infra.user.entities.PasswordResetEntity;
import com.otaku.rating.infra.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordResetEntityRepository extends JpaRepository<PasswordResetEntity, String> {
    Optional<PasswordResetEntity> findByUser(UserEntity user);

    @Modifying
    @Query("""
        DELETE FROM PasswordReset pr
        WHERE pr.user.id = :userId
        """)
    void deleteByUserId(@Param("userId") Long userId);

}
