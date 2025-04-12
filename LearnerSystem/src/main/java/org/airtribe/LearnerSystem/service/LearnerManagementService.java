package org.airtribe.LearnerSystem.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.airtribe.LearnerSystem.entity.Cohort;
import org.airtribe.LearnerSystem.entity.Course;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.exception.LearnerNotFoundException;
import org.airtribe.LearnerSystem.repository.CohortRepository;
import org.airtribe.LearnerSystem.repository.CourseRepository;
import org.airtribe.LearnerSystem.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LearnerManagementService {

  @Autowired
  private LearnerRepository _learnerRepository;

  @Autowired
  private CohortRepository _cohortRepository;

  @Autowired
  private CourseRepository _courseRepository;

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
    Optional<Cohort> cohort = _cohortRepository.findById(cohortId);
    if (learner.isPresent() && cohort.isPresent()) {
        Cohort fetchedCohort = cohort.get();
        fetchedCohort.getLearners().add(learner.get());
        return _cohortRepository.save(fetchedCohort);
    }
    return null;
  }

  public List<Cohort> getAllCohorts() {
    return _cohortRepository.findAll();
  }

  public Course createCourse(Course course) {
    return _courseRepository.save(course);
  }

  public Cohort assignLearnersToCohort(List<Learner> learners, Long cohortId) {
    Optional<Cohort> cohortOpt = _cohortRepository.findById(cohortId);
    if(cohortOpt.isPresent()) {
      Cohort cohort = cohortOpt.get();
      cohort.getLearners().addAll(learners);
      return _cohortRepository.save(cohort);
    }
    return null;
  }

  @Transactional
  public List<Cohort> fetchLearnerCohorts(Long learnerId) throws LearnerNotFoundException {
    Optional<Learner> learnerOpt = _learnerRepository.findById(learnerId);
    if (learnerOpt.isPresent()) {
      return learnerOpt.get().getCohorts();
    } else {
      throw new LearnerNotFoundException("Learner not found with ID: " + learnerId);
    }

  }
}
