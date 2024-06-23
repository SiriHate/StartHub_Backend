package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * This interface represents the repository for the News entity.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), count(), delete() etc.
 * It also extends JpaSpecificationExecutor which allows the execution of Specifications based on the JPA criteria API.
 * You can use these methods to perform database operations.
 * It is annotated with @Repository, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

}