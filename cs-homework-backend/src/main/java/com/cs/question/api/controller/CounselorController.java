package com.cs.question.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.question.api.common.model.ApiResponse;
import com.cs.question.api.common.util.JWTUtil;
import com.cs.question.api.entity.QuestionReceive;
import com.cs.question.api.entity.dto.CounselorDTO;
import com.cs.question.api.entity.dto.QuestionDTO;
import com.cs.question.api.entity.dto.QuestionReceiveDTO;
import com.cs.question.api.model.CounselorCreateDAO;
import com.cs.question.api.model.CounselorDAO;
import com.cs.question.api.model.QuestionReceiveDAO;
import com.cs.question.api.service.CounselorService;

@RestController
@RequestMapping("/counselors")
public class CounselorController {
  private final CounselorService counselorService;
  
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  
  @Autowired
  private JWTUtil jwtUtil;
  
  public CounselorController(CounselorService counselorService) {
    this.counselorService = counselorService;
  }
  
  @PostMapping
  public ResponseEntity<ApiResponse<CounselorDTO>> counselorCreate(@Valid @RequestBody CounselorCreateDAO dao) {
    dao.setPwd(this.passwordEncoder.encode(dao.getPwd()));
    CounselorDTO user = counselorService.createUser(dao);
    
    return new ResponseEntity<>(new ApiResponse<>(0, user), HttpStatus.CREATED);
  }
  
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<CounselorDTO>> counselorLogin(@Valid @RequestBody CounselorDAO dao) {
    CounselorDTO user = counselorService.readUser(dao, passwordEncoder);
    
    if (user == null) {
      throw new BadCredentialsException("user not found");
    }
    
    jwtUtil.createAndSetToken(user);
    
    return new ResponseEntity<>(new ApiResponse<>(0, user), HttpStatus.OK);
  }
  
  @Secured("ROLE_COUNSELOR")
  @GetMapping("/questions")
  public ResponseEntity<ApiResponse<List<QuestionDTO>>> getQuestion() {
    List<QuestionDTO> list = counselorService.getQuestion();
    
    return new ResponseEntity<>(new ApiResponse<>(0, list), HttpStatus.OK);
  }
  
  @Secured("ROLE_COUNSELOR")
  @GetMapping("/questions-own")
  public ResponseEntity<ApiResponse<List<QuestionReceiveDTO>>> getQuestionReceive(@AuthenticationPrincipal UserDetails counselor) {
    List<QuestionReceiveDTO> list = counselorService.getQuestionReceive(counselor.getUsername());
    
    return new ResponseEntity<>(new ApiResponse<>(0, list), HttpStatus.OK);
  }
  
  @Secured("ROLE_COUNSELOR")
  @PostMapping("/questions/{questionId}/receive")
  public ResponseEntity<ApiResponse<QuestionReceiveDTO>> receiveQuestion(@AuthenticationPrincipal UserDetails counselor, @PathVariable Long questionId) {
    QuestionReceiveDTO result = counselorService.receiveQuestion(counselor.getUsername(), questionId);
    
    return new ResponseEntity<>(new ApiResponse<>(0, result), HttpStatus.CREATED);
  }
  
  @Secured("ROLE_COUNSELOR")
  @PutMapping("/questions/{questionId}/response")
  public ResponseEntity<ApiResponse<QuestionReceiveDTO>> writeResponse(@AuthenticationPrincipal UserDetails counselor, @Valid @RequestBody QuestionReceiveDAO questionReceiveDao, @PathVariable Long questionId) {
    QuestionReceiveDTO result = counselorService.writeResponse(questionReceiveDao, counselor.getUsername(), questionId);
    
    return new ResponseEntity<>(new ApiResponse<>(0, result), HttpStatus.CREATED);
  }
}
