package org.siri_hate.main_service.repository;

import java.util.Optional;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.model.entity.Project;
import org.siri_hate.main_service.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByUsername(String username);

  @Query("SELECT a FROM Article a WHERE a.user.username = :username")
  Page<Article> findArticlesByUsername(String username, Pageable pageable);

  @Query("SELECT n FROM News n WHERE n.user.username = :username")
  Page<News> findNewsByUsername(String username, Pageable pageable);

  @Query("SELECT p FROM Project p WHERE p.user.username = :username")
  Page<Project> findOwnedProjectsByUsername(String username, Pageable pageable);

  @Query("SELECT p FROM Project p JOIN p.members m WHERE m.user.username = :username")
  Page<Project> findMemberProjectsByUsername(String username, Pageable pageable);
}
