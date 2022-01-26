package com.cs.question.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class CounselorCreateDTO {
  @NotNull
  @Size(min = 4, max = 20)
  private String id;
  
  @Size(min = 4, max = 12)
  @NotNull
  private String pwd;
  
  @Size(min = 2, max = 5)
  private String name;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getPwd() {
    return pwd;
  }
  
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  
}
