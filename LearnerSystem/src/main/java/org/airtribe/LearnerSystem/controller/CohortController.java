package org.airtribe.LearnerSystem.controller;

import java.util.List;
import org.airtribe.LearnerSystem.entity.Cohort;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.service.LearnerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CohortController {

  @Autowired
  private LearnerManagementService learnerManagementService;

  @PostMapping("/cohorts")
  public Cohort createCohort(@RequestBody Cohort cohort) {
    return learnerManagementService.createCohort(cohort);
  }

  @PostMapping("/assignLearners")
  public Cohort assignLearnerToCohort(@RequestParam("learnerId") Long learnerId, @RequestParam("cohortId") Long cohortId) {
    return learnerManagementService.assignLearnerToCohort(learnerId, cohortId);
  }

  @GetMapping("/cohorts")
  public List<Cohort> getAllCohorts() {
    return learnerManagementService.getAllCohorts();
  }

  @PostMapping("/cohorts/{cohortId}/learners")
  public Cohort assignAndCreateLearnersToCohorts(@RequestBody List<Learner> learners, @PathVariable("cohortId") Long cohortId) {
    return learnerManagementService.assignLearnersToCohort(learners, cohortId);
  }


}