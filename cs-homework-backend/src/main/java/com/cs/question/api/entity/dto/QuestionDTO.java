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
public class QuestionDTO {
  private Long seq;
  private String customerId;
  private String title;
  private String content;
  private LocalDateTime regDate;
  private String counselorId;
  private String responseContent;
  private LocalDateTime createDate;
  private LocalDateTime responseDate;
  private Integer checkState;
  private String counselorName;
  
  public static QuestionDTO readFrom(Question q) {
    if (q == null) return null;
    
    QuestionDTOBuilder builder = QuestionDTO.builder()
                                              .seq(q.getSeq())
                                              .customerId(q.getCustomerId())
                                              .title(q.getTitle())
                                              .content(q.getContent())
                                              .regDate(q.getRegDate());
    
    if (q.getQuestionReceive() != null) {
      QuestionReceive r = q.getQuestionReceive();
      builder
        .checkState(r.getCheckState())
        .counselorId(r.getCounselorId())
        .responseContent(r.getContent())
        .createDate(r.getCreateDate())
        .responseDate(r.getResponseDate());
      
      if(r.getCounselor() != null) {
        builder.counselorName(r.getCounselor().getName());
      }
    }
    
    return builder.build();
  }
}
