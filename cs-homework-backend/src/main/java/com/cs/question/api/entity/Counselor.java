package com.cs.question.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cs.question.api.model.CounselorCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Counselor")
@DynamicInsert
public class Counselor implements Serializable, UserDetails {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "Id")
  private String id;
  
  @Column(name = "Name")
  private String name;
  
  @JsonIgnore
  @Column(name = "Pwd")
  private String pwd;
  
  @CreationTimestamp
  @Column(name = "RegDate")
  private LocalDateTime regDate;
  
  public Counselor() {
    
  }
  
  public Counselor(CounselorCreateDTO dto) {
    this.id = dto.getId();
    this.name = dto.getName();
    this.pwd = dto.getPwd();
  }
  
  public Counselor(String id, String name, String pwd) {
    this.id = id;
    this.name = name;
    this.pwd = pwd;
  }
  
  @Transient
  private Collection<? extends GrantedAuthority> authorities;
  
  @Transient
  private String token;
  
  public String getToken() {
    return token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getPwd() {
    return pwd;
  }
  
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  
  public LocalDateTime getRegDate() {
    return regDate;
  }
  
  public void setRegDate(LocalDateTime regDate) {
    this.regDate = regDate;
  }
  
  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }
  
  @Override
  public String getPassword() {
    return this.pwd;
  }
  
  @Override
  public String getUsername() {
    return this.id;
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return false;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return false;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }
  
  @Override
  public boolean isEnabled() {
    return false;
  }
  
}
