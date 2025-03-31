package org.airtribe.LearnerSystem.service;

import java.util.List;
import java.util.Optional;
import org.airtribe.LearnerSystem.entity.Cohort;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.exception.LearnerNotFoundException;
import org.airtribe.LearnerSystem.repository.CohortRepository;
import org.airtribe.LearnerSystem.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LearnerManagementService {

  @Autowired
  private LearnerRepository _learnerRepository;

  @Autowired
  private CohortRepository _cohortRepository;

  public Learner createLearner(Learner learner) {
    return _learnerRepository.save(learner);
  }

  public List<Learner> getAllLearners() {
    return _learnerRepository.findAll();
  }

  public Learner findLearnerById(Long learnerId) throws LearnerNotFoundException {
    if(_learnerRepository.findById(learnerId).isPresent()) {
      return _learnerRepository.findById(learnerId).get();
    }
    throw new LearnerNotFoundException("Learn not found, incorrect id provided " + learnerId);
  }

  public Learner findLearnerByName(String learnerName) {
    return _learnerRepository.findName(learnerName);
  }

  public Cohort createCohort(Cohort cohort) {
    return _cohortRepository.save(cohort);
  }

  public Cohort assignLearnerToCohort(Long learnerId, Long cohortId) {
    Optional<Learner> learner = _learnerRepository.findById(learnerId);
    if (learner.isPresent()) {
      Optional<Cohort> cohort = _cohortRepository.findById(cohortId);
      if (cohort.isPresent()) {
        Cohort fetchedCohort = cohort.get();
        fetchedCohort.getLearners().add(learner.get());
        return _cohortRepository.save(fetchedCohort);
      }
    }
    return null;
  }

  public List<Cohort> getAllCohorts() {
    return _cohortRepository.findAll();
  }
}
