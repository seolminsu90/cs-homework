package com.cs.question.api.entity.dto;

import java.time.LocalDateTime;

import com.cs.question.api.entity.Question;
import com.cs.question.api.entity.QuestionReceive;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Builder
public class QuestionReceiveDTO {
  private Long questionSeq;
  private String counselorId;
  private String content;
  private LocalDateTime createDate;
  private LocalDateTime responseDate;
  private Integer checkState;
  
  private String customerId;
  private String title;
  private String questionContent;
  private LocalDateTime regDate;
  private String counselorName;
  
  public static QuestionReceiveDTO readFrom(QuestionReceive q) {
    QuestionReceiveDTOBuilder builder =  QuestionReceiveDTO.builder()
                                .questionSeq(q.getQuestionSeq())
                                .counselorId(q.getCounselorId())
                                .content(q.getContent())
                                .createDate(q.getCreateDate())
                                .responseDate(q.getResponseDate())
                                .checkState(q.getCheckState());
    if(q.getQuestion() != null) {
      Question qe = q.getQuestion();
      builder.customerId(qe.getCustomerId())
           .title(qe.getTitle())
           .questionContent(qe.getContent())
           .regDate(qe.getRegDate());
    }
    if(q.getCounselor() != null) {
      builder.counselorName(q.getCounselor().getName());
    }
    return builder.build();
  }
}
