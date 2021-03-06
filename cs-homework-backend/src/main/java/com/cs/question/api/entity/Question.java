package com.cs.question.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.cs.question.api.model.QuestionDAO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Question")
@DynamicInsert
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Question implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "Seq")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;
  
  @Column(name = "CustomerId")
  private String customerId;
  
  @Column(name = "Title")
  private String title;
  
  @Column(name = "Content")
  private String content;
  
  @CreationTimestamp
  @Column(name = "RegDate")
  private LocalDateTime regDate;
  
  @OneToOne
  @JoinColumn(name = "Seq", updatable = false, insertable = false)
  private QuestionReceive questionReceive;
  
  public Question() {
  }
  
  public Question(String customerId, String title, String content) {
    this.customerId = customerId;
    this.title = title;
    this.content = content;
  }
  
  public Question(QuestionDAO dao) {
    this.customerId = dao.getCustomerId();
    this.title = dao.getTitle();
    this.content = dao.getContent();
  }
  
  public QuestionReceive getQuestionReceive() {
    return questionReceive;
  }
  
  public void setQuestionReceive(QuestionReceive questionReceive) {
    this.questionReceive = questionReceive;
  }
  
  public Long getSeq() {
    return seq;
  }
  
  public void setSeq(Long seq) {
    this.seq = seq;
  }
  
  public String getCustomerId() {
    return customerId;
  }
  
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getContent() {
    return content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public LocalDateTime getRegDate() {
    return regDate;
  }
  
  public void setRegDate(LocalDateTime regDate) {
    this.regDate = regDate;
  }
  
}
