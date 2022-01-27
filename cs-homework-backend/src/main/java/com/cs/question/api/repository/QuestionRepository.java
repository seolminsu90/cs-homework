package com.cs.question.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cs.question.api.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
  
  @EntityGraph(attributePaths = {"questionReceive", "questionReceive.counselor"})
  List<Question> findByCustomerIdOrderBySeqDesc(String customerId);
  
  @Query(value = "SELECT A FROM Question A LEFT JOIN FETCH A.questionReceive WHERE A.questionReceive IS EMPTY ORDER BY A.regDate DESC")
  List<Question> findByNoReadQuestions();
}
