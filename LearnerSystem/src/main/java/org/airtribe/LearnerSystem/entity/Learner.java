package org.airtribe.LearnerSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;


@Entity
public class Learner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long learnerId;
  private String name;
  private String username;
  private String password;

  @ManyToMany(mappedBy = "learners")
  @JsonIgnore
  private List<Cohort> cohorts;

  public Learner() {
  }

  public Learner(Long learnerId, String name, String username, String password) {
    this.learnerId = learnerId;
    this.name = name;
    this.username = username;
    this.password = password;
  }

  public List<Cohort> getCohorts() {
    return cohorts;
  }

  public void setCohorts(List<Cohort> cohorts) {
    this.cohorts = cohorts;
  }

  public Long getLearnerId() {
    return learnerId;
  }

  public void setLearnerId(Long learnerId) {
    this.learnerId = learnerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
