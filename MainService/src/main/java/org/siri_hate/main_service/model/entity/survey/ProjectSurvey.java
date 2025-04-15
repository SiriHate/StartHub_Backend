package org.siri_hate.main_service.model.entity.survey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import org.siri_hate.main_service.model.entity.Project;

@Entity
@Table(name = "project_surveys")
public class ProjectSurvey {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "project_id", nullable = false, unique = true)
  private Project project;

  @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SurveyQuestion> questions = new ArrayList<>();

  public ProjectSurvey() {}

  public ProjectSurvey(Long id, Project project, List<SurveyQuestion> questions) {
    this.id = id;
    this.project = project;
    this.questions = questions;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public List<SurveyQuestion> getQuestions() {
    return questions;
  }

  public void setQuestions(List<SurveyQuestion> questions) {
    this.questions = questions;
  }
}
