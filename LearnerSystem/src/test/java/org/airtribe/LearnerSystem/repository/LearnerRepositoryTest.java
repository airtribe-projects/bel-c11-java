package org.airtribe.LearnerSystem.repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.airtribe.LearnerSystem.entity.Learner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LearnerRepositoryTest {


  @Autowired
  private DataSource dataSource;

  @Autowired
  private LearnerRepository _learnerRepository;

  private Learner _learner;

  @BeforeEach
  public void setup() {

    _learner = new Learner(null, "test", "test", "test");
  }

  @Test
  public void testSaveLearnerSuccessfully() {
    Learner expectedLearner = _learnerRepository.save(_learner);
    assertNotNull(expectedLearner.getLearnerId());
    assertEquals("test", expectedLearner.getName());
  }

  @Test
  public void testFetchLearnerByIdSuccessfully() {
    Learner savedLearner = _learnerRepository.save(_learner);
    Optional<Learner> learnerOptional = _learnerRepository.findById(savedLearner.getLearnerId());
    assertNotNull(learnerOptional);
    assertNotNull(learnerOptional.get());
    assertEquals(savedLearner.getLearnerId(), learnerOptional.get().getLearnerId());
  }

  @Test
  void testPrintDBType() {
    try (Connection con = dataSource.getConnection()) {
      System.out.println("DB: " + con.getMetaData().getDatabaseProductName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
