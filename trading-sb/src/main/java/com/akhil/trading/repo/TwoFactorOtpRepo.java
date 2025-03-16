package com.akhil.trading.repo;

import com.akhil.trading.model.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TwoFactorOtpRepo extends JpaRepository<TwoFactorOTP,String> {
    Optional<TwoFactorOTP> findByUserId(Long userId);
}
