package org.airtribe.LearnerSystem.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;


@Entity
public class Cohort {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cohortId;

  private String cohortName;


  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})

  private List<Learner> learners;

  @ManyToOne
  private Course course;

  public Cohort(Long cohortId, String cohortName, List<Learner> learners) {
    this.cohortId = cohortId;
    this.cohortName = cohortName;
    this.learners = learners;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Cohort() {

  }

  public Long getCohortId() {
    return cohortId;
  }

  public void setCohortId(Long cohortId) {
    this.cohortId = cohortId;
  }

  public String getCohortName() {
    return cohortName;
  }

  public void setCohortName(String cohortName) {
    this.cohortName = cohortName;
  }

  public List<Learner> getLearners() {
    return learners;
  }

  public void setLearners(List<Learner> learners) {
    this.learners = learners;
  }
}
