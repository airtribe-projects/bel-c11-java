package org.airtribe.LearnerSystem.dto;

import java.util.List;


public class LearnerDTO {
  private Long learnerId;

  private String name;

  private List<Long> cohortIds;

  public LearnerDTO(Long learnerId, String name, List<Long> cohortIds) {
    this.learnerId = learnerId;
    this.name = name;
    this.cohortIds = cohortIds;
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

  public List<Long> getCohortIds() {
    return cohortIds;
  }

  public void setCohortIds(List<Long> cohortIds) {
    this.cohortIds = cohortIds;
  }
}
