package org.siri_hate.user_service.repository;

import java.util.Optional;
import org.siri_hate.user_service.model.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

  Optional<ConfirmationToken> findConfirmationTokenByTokenValue(String token);
}
