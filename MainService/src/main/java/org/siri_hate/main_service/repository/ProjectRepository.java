package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository
    extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
  
  Page<Project> findByModerationPassedTrue(Pageable pageable);
  
  Page<Project> findByModerationPassedFalse(Pageable pageable);
  
  Page<Project> findByUserUsername(String username, Pageable pageable);
  
  Page<Project> findByMembersUserUsername(String username, Pageable pageable);
}
