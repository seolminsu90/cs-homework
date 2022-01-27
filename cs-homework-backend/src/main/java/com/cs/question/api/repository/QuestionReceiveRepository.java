package com.cs.question.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cs.question.api.entity.QuestionReceive;

public interface QuestionReceiveRepository extends CrudRepository<QuestionReceive, Long> {
  Optional<QuestionReceive> findByQuestionSeqAndCounselorId(Long questionSeq, String counselorId);
  
  @Query(value = "SELECT A FROM QuestionReceive A JOIN FETCH A.question JOIN FETCH A.counselor WHERE A.counselorId = :counselorId ORDER BY A.checkState ASC")
  List<QuestionReceive> findByCounselorIdOrderByCheckStateAsc(@Param("counselorId") String counselorId);
}
