package org.airtribe.AuthenticationAuthorizationC11.repository;

import org.airtribe.AuthenticationAuthorizationC11.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
  VerificationToken findByToken(String token);
}
