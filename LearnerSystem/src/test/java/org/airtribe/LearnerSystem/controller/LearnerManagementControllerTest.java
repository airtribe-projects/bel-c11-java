package org.airtribe.LearnerSystem.controller;

import java.util.List;
import org.airtribe.LearnerSystem.entity.Learner;
import org.airtribe.LearnerSystem.service.LearnerManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LearnerManagementControllerTest {

  @Mock
  private LearnerManagementService _learnerManagementService;

  @Autowired
  private MockMvc _mockMvc;

  private List<Learner> _learnerList;

  @InjectMocks
  private LearnerManagementController _learnerManagementController;

  @BeforeEach
  public void setup() {
    _learnerList = List.of(new Learner(1L, "John Doe", "john", "12345"));
    _mockMvc = MockMvcBuilders.standaloneSetup(_learnerManagementController).build();
  }


  @Test
  void testGetLearner() throws Exception {
    when(_learnerManagementService.getAllLearners()).thenReturn(_learnerList);
    _mockMvc.perform(get("/learners")).andExpect(status().isOk())
        .andDo(print()).andExpect(jsonPath("$[0].learnerId").doesNotExist())

        .andExpect(jsonPath("$[0].name").value("John Doe"))
        .andExpect(jsonPath("$[0].username").value("john"));
  }

}
