package com.cs.question.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.cs.question.api.common.config.exception.CustomException;
import com.cs.question.api.entity.Counselor;
import com.cs.question.api.entity.Question;
import com.cs.question.api.entity.QuestionReceive;
import com.cs.question.api.model.CounselorCreateDTO;
import com.cs.question.api.model.QuestionReceiveDTO;
import com.cs.question.api.repository.CounselorRepository;
import com.cs.question.api.repository.QuestionReceiveRepository;
import com.cs.question.api.repository.QuestionRepository;

@Service
public class CounselorService {
  private final CounselorRepository counselorRepository;
  private final QuestionRepository questionRepository;
  private final QuestionReceiveRepository questReceiveRepository;
  
  public CounselorService(CounselorRepository counselorRepository, QuestionRepository questionRepository, QuestionReceiveRepository questReceiveRepository) {
    this.counselorRepository = counselorRepository;
    this.questionRepository = questionRepository;
    this.questReceiveRepository = questReceiveRepository;
  }
  
  public void createUser(CounselorCreateDTO dto) {
    Optional<Counselor> isExistsChk = counselorRepository.findById(dto.getId());
    if (isExistsChk.isPresent()) throw new CustomException(1400, "이미 동일한 아이디의 상담사가 존재합니다.");
    
    Counselor counselor = new Counselor(dto);
    counselorRepository.save(counselor);
  }
  
  public Counselor readUser(String id) {
    Optional<Counselor> optionalUser = counselorRepository.findById(id);
    if (!optionalUser.isPresent()) return null;
    
    Counselor user = optionalUser.get();
    user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_COUNSELOR")));
    return user;
  }
  
  public List<Question> getQuestion() {
    return questionRepository.findByNoReadQuestions();
  }
  
  public List<QuestionReceive> getQuestionReceive(String id) {
    return questReceiveRepository.findByCounselorIdOrderByCheckStateAsc(id);
  }
  
  public QuestionReceive receiveQuestion(String id, Long questionId) {
    Question question = questionRepository.findById(questionId).orElseThrow(() -> new CustomException(1404, "문의를 찾을 수 없습니다."));
    
    QuestionReceive receiveInfo = question.getQuestionReceive();
    if (receiveInfo == null) {
      QuestionReceive questionReceive = new QuestionReceive();
      questionReceive.setQuestionSeq(questionId);
      questionReceive.setCounselorId(id);
      return questReceiveRepository.save(questionReceive);
    } else {
      throw new CustomException(1405, "이미 할당 된 문의입니다.");
    }
  }
  
  public QuestionReceive writeResponse(QuestionReceiveDTO questionReceiveDTO, String id, Long questionId) {
    QuestionReceive saveReponse = questReceiveRepository.findByQuestionSeqAndCounselorId(questionId, id).orElseThrow(() -> new CustomException(1406, "문의가 없거나 상담사에게 할당된 문의가 아닙니다."));
    
    saveReponse.setContent(questionReceiveDTO.getContent());
    saveReponse.setCheckState(1);
    
    return questReceiveRepository.save(saveReponse);
  }
}