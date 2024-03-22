package com.dailycodework.sbemailverificationdemo.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
   VerificationToken findByToken(String token);
}
