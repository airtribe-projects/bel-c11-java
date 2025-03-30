package org.airtribe.LearnerSystem.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.exception.LearnerNotFoundException;
import org.airtribe.LearnerSystem.repository.LearnerRepository;
import org.airtribe.LearnerSystem.repository.LearnerRepositoryManual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LearnerManagementService {

  @Autowired
  private LearnerRepositoryManual _learnerRepository;

  public int createLearner(Learner learner) {
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
}
