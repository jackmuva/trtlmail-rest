package com.jackmu.slowcapsules.repository.security;

import com.jackmu.slowcapsules.model.security.PasswordResetToken;
import com.jackmu.slowcapsules.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

    List<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    @Modifying
    @Query(value = "delete from PasswordResetToken t where t.expiryDate <= NOW()", nativeQuery = true)
    void deleteAllExpired();
}
