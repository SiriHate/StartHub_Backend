package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    Optional<Moderator> findModeratorByUsername(String username);
}
