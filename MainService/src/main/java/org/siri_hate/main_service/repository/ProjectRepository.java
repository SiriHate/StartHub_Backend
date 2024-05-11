package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectsByProjectNameContainingIgnoreCase(String projectName);
    List<Project> findProjectsByProjectOwner(String username);
 }
