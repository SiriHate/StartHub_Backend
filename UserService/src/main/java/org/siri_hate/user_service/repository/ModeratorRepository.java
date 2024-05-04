package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("moderatorRepository")
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    Optional<Moderator> findModeratorByUsername(String username);
    List<Moderator> findModeratorByUsernameStartingWithIgnoreCase(String username);
}
