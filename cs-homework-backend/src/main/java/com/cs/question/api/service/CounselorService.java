package com.cs.question.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cs.question.api.common.config.exception.CustomException;
import com.cs.question.api.entity.Counselor;
import com.cs.question.api.entity.Question;
import com.cs.question.api.entity.QuestionReceive;
import com.cs.question.api.entity.dto.CounselorDTO;
import com.cs.question.api.entity.dto.QuestionDTO;
import com.cs.question.api.entity.dto.QuestionReceiveDTO;
import com.cs.question.api.model.CounselorCreateDAO;
import com.cs.question.api.model.CounselorDAO;
import com.cs.question.api.model.QuestionReceiveDAO;
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
  
  public CounselorDTO createUser(CounselorCreateDAO dao) {
    Optional<Counselor> isExistsChk = counselorRepository.findById(dao.getId());
    if (isExistsChk.isPresent()) throw new CustomException(1400, "이미 동일한 아이디의 상담사가 존재합니다.");
    
    Counselor user = new Counselor(dao);
    counselorRepository.save(user);
    
    return CounselorDTO.readFrom(user);
  }
  
  public CounselorDTO readUser(CounselorDAO dao, BCryptPasswordEncoder passwordEncoder) {
    Optional<Counselor> optionalUser = counselorRepository.findById(dao.getId());
    if (!optionalUser.isPresent()) return null;
    
    Counselor user = optionalUser.get();
    
    if (!passwordEncoder.matches(dao.getPwd(), user.getPassword())) throw new BadCredentialsException("password is not matched");
    
    user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_COUNSELOR")));
    
    return CounselorDTO.readFrom(user);
  }
  
  public List<QuestionDTO> getQuestion() {
    List<Question> list = questionRepository.findByNoReadQuestions();
    return list.stream().map(QuestionDTO::readFrom).toList();
  }
  
  public List<QuestionReceiveDTO> getQuestionReceive(String id) {
    List<QuestionReceive> list = questReceiveRepository.findByCounselorIdOrderByCheckStateAsc(id);
    return list.stream().map(QuestionReceiveDTO::readFrom).toList();
  }
  
  public QuestionReceiveDTO receiveQuestion(String id, Long questionId) {
    Question question = questionRepository.findById(questionId).orElseThrow(() -> new CustomException(1404, "문의를 찾을 수 없습니다."));
    
    QuestionReceive receiveInfo = question.getQuestionReceive();
    if (receiveInfo == null) {
      QuestionReceive questionReceive = new QuestionReceive();
      questionReceive.setQuestionSeq(questionId);
      questionReceive.setCounselorId(id);
      questReceiveRepository.save(questionReceive);
      return QuestionReceiveDTO.readFrom(questionReceive);
    } else {
      throw new CustomException(1405, "이미 할당 된 문의입니다.");
    }
  }
  
  public QuestionReceiveDTO writeResponse(QuestionReceiveDAO questionReceiveDao, String id, Long questionId) {
    QuestionReceive saveReponse = questReceiveRepository.findByQuestionSeqAndCounselorId(questionId, id).orElseThrow(() -> new CustomException(1406, "문의가 없거나 상담사에게 할당된 문의가 아닙니다."));
    
    saveReponse.setContent(questionReceiveDao.getContent());
    saveReponse.setCheckState(1);
    questReceiveRepository.save(saveReponse);
    
    return QuestionReceiveDTO.readFrom(saveReponse);
  }
}