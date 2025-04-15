package org.siri_hate.main_service.model.entity.survey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_questions")
public class SurveyQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "survey_id", nullable = false)
  private ProjectSurvey survey;

  @Column(name = "question_text", nullable = false)
  private String questionText;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SurveyAnswer> answers = new ArrayList<>();

  public SurveyQuestion() {}

  public SurveyQuestion(Long id, ProjectSurvey survey, String questionText) {
    this.id = id;
    this.survey = survey;
    this.questionText = questionText;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProjectSurvey getSurvey() {
    return survey;
  }

  public void setSurvey(ProjectSurvey survey) {
    this.survey = survey;
  }

  public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public List<SurveyAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<SurveyAnswer> answers) {
    this.answers = answers;
  }
}
