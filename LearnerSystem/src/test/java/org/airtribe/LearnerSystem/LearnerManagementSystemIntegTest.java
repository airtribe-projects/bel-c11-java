package org.airtribe.LearnerSystem;

import jakarta.transaction.Transactional;
import java.util.List;
import org.airtribe.LearnerSystem.controller.LearnerManagementController;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.repository.LearnerRepository;
import org.airtribe.LearnerSystem.service.LearnerManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LearnerManagementSystemIntegTest {

  @Autowired
  private MockMvc _mockMvc;

  private List<Learner> _learnerList;


  @Autowired
  private LearnerRepository _learnerRepository;


  @BeforeEach
  public void setup() {
    _learnerRepository.deleteAll();
    _learnerList = List.of(new Learner(1L, "John Doe", "john", "12345"));
  }

  @Test
  @DisplayName("Test to get all learners")
  public void testCreateNewLearner() throws Exception {
    _mockMvc.perform(post("/learners")
        .contentType("application/json")
        .content("{\"name\":\"John Doe\", \"username\":\"john\", \"password\":\"12345\"}"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.name").value("John Doe"))
        .andExpect(jsonPath("$.username").value("john"));
  }
}
