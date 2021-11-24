package org.lnu.software.testing.auto.service.repository.user;

import org.lnu.software.testing.auto.service.entity.user.UserEntity;
import org.lnu.software.testing.auto.service.projection.UserAuthDataProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserAuthDataProjection findUserAuthDataByUsername(String username);

    @Modifying
    @Query("DELETE FROM UserEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
