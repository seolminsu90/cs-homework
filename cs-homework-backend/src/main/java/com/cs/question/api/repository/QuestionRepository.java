package com.cs.question.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cs.question.api.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

	List<Question> findByCustomerIdOrderBySeqDesc(String customerId);

	@Query(value = "SELECT A FROM Question A LEFT JOIN QuestionReceive B ON A.seq = B.questionSeq WHERE B.questionSeq IS NULL")
	List<Question> findByNoReadQuestions();
}
