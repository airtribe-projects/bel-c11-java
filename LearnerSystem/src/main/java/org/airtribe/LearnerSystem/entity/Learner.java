package org.airtribe.LearnerSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Learner {

  @Id
  private Long learnerId;
  private String name;
  private String username;
  private String password;

  public Learner() {
  }

  public Learner(Long learnerId, String name, String username, String password, String course, String email) {
    this.learnerId = learnerId;
    this.name = name;
    this.username = username;
    this.password = password;
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
