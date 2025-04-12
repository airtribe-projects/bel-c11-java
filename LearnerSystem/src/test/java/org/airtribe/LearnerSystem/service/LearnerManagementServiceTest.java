package org.airtribe.LearnerSystem.service;

import java.util.Optional;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.exception.LearnerNotFoundException;
import org.airtribe.LearnerSystem.repository.LearnerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class LearnerManagementServiceTest {


  @InjectMocks
  private LearnerManagementService _learnerManagementService;

  @Mock
  public LearnerRepository _learnerRepository;

  private Learner _learner;

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Before all running once before all tests");
  }

  @BeforeEach
  public void setup() {
    System.out.println("Before each running once before each test");
    _learner = new Learner(100L, "test", "test", "test");
  }

  @Test
  public void testSaveLearnerSuccessfully() {
    System.out.println("Test save learner successfully");
    when(_learnerRepository.save(any())).thenReturn(_learner);
    Learner expectedLearner = _learnerManagementService.createLearner(_learner);

    assertEquals(expectedLearner.getName(), "test");
    assertEquals(expectedLearner.getUsername(), "test");
    assertEquals(expectedLearner.getPassword(), "test");
    assertEquals(expectedLearner.getLearnerId(), 100L);
  }

  @Test
  public void testFindLearnerById_Successfully() throws LearnerNotFoundException {
    System.out.println("Test find learner by id successfully");
    when(_learnerRepository.findById(100L)).thenReturn(Optional.of(_learner));
    Learner fetchedLearner = _learnerManagementService.findLearnerById(100L);
    assertEquals(fetchedLearner, _learner);
  }

  @Test()
  public void testFindLearnerById_learnerNotFound() throws LearnerNotFoundException {
    when(_learnerRepository.findById(any())).thenReturn(Optional.empty());
    try {
      _learnerManagementService.findLearnerById(100L);
    } catch (LearnerNotFoundException exception) {
      assertEquals(exception.getMessage(), "Learn not found, incorrect id provided 100");
    }
  }
}
