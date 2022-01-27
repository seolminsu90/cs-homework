package com.cs.question.api;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cs.question.api.model.CounselorCreateDAO;
import com.cs.question.api.model.QuestionDAO;
import com.cs.question.api.model.QuestionReceiveDAO;
import com.cs.question.api.service.CounselorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class ControllerTest {
  
  @Autowired
  private ObjectMapper objectMapper;
  
  private MockMvc mvc;
  
  @Autowired
  private WebApplicationContext context;
  
  @MockBean
  private CounselorService counselorService;
  
  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }
  
  @Test
  public void customerGetQuestion() throws Exception {
    this.mvc.perform(get("/customers/questions").param("customerId", "Tester")).andDo(print()).andExpect(status().isOk());
  }
  
  @Test
  public void customerWriteQuestion() throws Exception {
    QuestionDAO question = new QuestionDAO();
    question.setCustomerId("MockupTester");
    question.setTitle("안녕하세요, 반갑습니다");
    question.setContent("안녕히계세요.");
    
    String bodyString = objectMapper.writeValueAsString(question);
    
    this.mvc.perform(post("/customers/questions").content(bodyString).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
  }
  
  @Test
  public void counselorCreate() throws Exception {
    CounselorCreateDAO counselor = new CounselorCreateDAO();
    counselor.setId("Counsler1");
    counselor.setPwd("1234");
    counselor.setName("테스터");
    
    String bodyString = objectMapper.writeValueAsString(counselor);
    this.mvc.perform(post("/counselors").content(bodyString).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
  }
  
  @Test
  @WithMockUser(username = "Counselor1", roles = { "COUNSELOR" })
  public void counselorGetQuestion() throws Exception {
    this.mvc.perform(get("/counselors/questions")).andDo(print()).andExpect(status().isOk());
  }
  
  @Test
  @WithMockUser(username = "Counselor1", roles = { "COUNSELOR" })
  public void counselorReceiveQuestion() throws Exception {
    
    this.mvc.perform(post("/counselors/questions/1/receive")).andDo(print()).andExpect(status().isCreated());
  }
  
  @Test
  @WithMockUser(username = "Counselor1", roles = { "COUNSELOR" })
  public void counselorResponseQuestion() throws Exception {
    
    QuestionReceiveDAO questionReceive = new QuestionReceiveDAO();
    questionReceive.setContent("Test...Test...Test...Test...");
    
    String bodyString = objectMapper.writeValueAsString(questionReceive);
    
    this.mvc.perform(put("/counselors/questions/1/response").content(bodyString).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
  }
}
