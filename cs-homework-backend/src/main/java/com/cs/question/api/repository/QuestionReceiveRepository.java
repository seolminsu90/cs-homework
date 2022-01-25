package com.cs.question.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.cs.question.api.entity.QuestionReceive;

public interface QuestionReceiveRepository extends CrudRepository<QuestionReceive, Long> {
	public Optional<QuestionReceive> findByQuestionSeqAndCounselorId(Long questionSeq, String counselorId);

	@EntityGraph(attributePaths = "question")
	public List<QuestionReceive> findByCounselorIdOrderByCheckStateDesc(String counselorId);
}
