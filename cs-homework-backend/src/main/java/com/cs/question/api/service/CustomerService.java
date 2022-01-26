package com.cs.question.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cs.question.api.entity.Question;
import com.cs.question.api.model.QuestionDTO;
import com.cs.question.api.repository.QuestionRepository;

@Service
public class CustomerService {
  private final QuestionRepository questionRepository;

  public CustomerService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public List<Question> findQuestion(String customerId) {
    System.out.println("why -- " + customerId);
    return questionRepository.findByCustomerIdOrderBySeqDesc(customerId);
  }

  public Question writeQuestion(QuestionDTO dto) {
    Question question = new Question(dto);

    return questionRepository.save(question);
  }
}
