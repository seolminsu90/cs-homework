package com.cs.question.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "QuestionReceive")
@DynamicInsert
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class QuestionReceive implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "QuestionSeq")
  private Long questionSeq;
  
  @Column(name = "CounselorId")
  private String counselorId;
  
  @Column(name = "Content")
  private String content;
  
  @CreationTimestamp
  @Column(name = "CreateDate")
  private LocalDateTime createDate;
  
  @UpdateTimestamp
  @Column(name = "ResponseDate")
  private LocalDateTime responseDate;
  
  @OneToOne(mappedBy = "questionReceive")
  private Question question;
  
  @OneToOne
  @JoinColumn(name = "CounselorId", updatable = false, insertable = false)
  private Counselor counselor;
  
  public Counselor getCounselor() {
    return counselor;
  }
  
  public void setCounselor(Counselor counselor) {
    this.counselor = counselor;
  }
  
  public LocalDateTime getResponseDate() {
    return responseDate;
  }
  
  public void setResponseDate(LocalDateTime responseDate) {
    this.responseDate = responseDate;
  }
  
  @Column(name = "CheckState")
  private Integer checkState;
  
  public Question getQuestion() {
    return question;
  }
  
  public void setQuestion(Question question) {
    this.question = question;
  }
  
  public Long getQuestionSeq() {
    return questionSeq;
  }
  
  public void setQuestionSeq(Long questionSeq) {
    this.questionSeq = questionSeq;
  }
  
  public String getCounselorId() {
    return counselorId;
  }
  
  public void setCounselorId(String counselorId) {
    this.counselorId = counselorId;
  }
  
  public String getContent() {
    return content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public LocalDateTime getCreateDate() {
    return createDate;
  }
  
  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }
  
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  
  public Integer getCheckState() {
    return checkState;
  }
  
  public void setCheckState(Integer checkState) {
    this.checkState = checkState;
  }
  
}
