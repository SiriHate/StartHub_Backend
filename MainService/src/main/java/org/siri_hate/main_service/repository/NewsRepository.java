package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findNewsByOwner(String username);
}
