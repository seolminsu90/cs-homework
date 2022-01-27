package com.cs.question.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cs.question.api.entity.Question;
import com.cs.question.api.entity.dto.QuestionDTO;
import com.cs.question.api.model.QuestionDAO;
import com.cs.question.api.repository.QuestionRepository;

@Service
public class CustomerService {
  private final QuestionRepository questionRepository;
  
  public CustomerService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }
  
  public List<QuestionDTO> findQuestion(String customerId) {
    List<Question> list = questionRepository.findByCustomerIdOrderBySeqDesc(customerId);
    
    return list.stream().map(QuestionDTO::readFrom).toList();
  }
  
  public QuestionDTO writeQuestion(QuestionDAO dao) {
    Question question = new Question(dao);
    
    return QuestionDTO.readFrom(questionRepository.save(question));
  }
}
