package org.airtribe.LearnerSystem.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.airtribe.LearnerSystem.entity.Cohort;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.exception.LearnerNotFoundException;
import org.airtribe.LearnerSystem.service.LearnerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LearnerManagementController {

  @Autowired
  private LearnerManagementService _learnerManagementService;

  @PostMapping("/learners")
  public Learner createLearner(@Valid @RequestBody Learner learner) {
    return _learnerManagementService.createLearner(learner);
  }

  @GetMapping("/learners")
  public List<Learner> fetchLearnerByName(
      @RequestParam(value = "learnerName", required = false) String learnerName,
      @RequestParam(value = "learnerId", required = false) Long learnerId) throws LearnerNotFoundException {
    if (learnerName== null && learnerId == null) {
      return _learnerManagementService.getAllLearners();
    }
    if (learnerId == null) {
      Learner learner = _learnerManagementService.findLearnerByName(learnerName);
      return List.of(learner);

    }

    Learner learner = _learnerManagementService.findLearnerById(learnerId);

    return List.of(learner);
  }


  @GetMapping("/learner/{learnerId}/cohorts")
  public List<Cohort> fetchLearnerCohorts(@PathVariable Long learnerId) throws LearnerNotFoundException {
    return _learnerManagementService.fetchLearnerCohorts(learnerId);
  }


  @ExceptionHandler(LearnerNotFoundException.class)
  public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e) {
    return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
  }


}
