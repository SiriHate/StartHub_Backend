package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    Page<News> findByModerationPassedTrue(Pageable pageable);

    Page<News> findByModerationPassedFalse(Pageable pageable);

    Page<News> findByUserUsername(String username, Pageable pageable);
}
