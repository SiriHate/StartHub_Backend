package org.siri_hate.user_service.repository;

import java.util.Optional;
import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {

  Optional<Moderator> findModeratorByUsername(String username);

  Page<Moderator> findModeratorByUsernameStartingWithIgnoreCase(String username, Pageable pageable);
}
