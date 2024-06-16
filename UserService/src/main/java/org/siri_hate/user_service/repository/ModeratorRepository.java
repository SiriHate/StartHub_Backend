package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {

    Optional<Moderator> findModeratorByUsername(String username);

    Page<Moderator> findModeratorByUsernameStartingWithIgnoreCase(String username, Pageable pageable);

}
