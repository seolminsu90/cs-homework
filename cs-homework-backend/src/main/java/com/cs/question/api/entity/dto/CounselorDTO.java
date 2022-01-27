package com.cs.question.api.entity.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.cs.question.api.entity.Counselor;
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
public class CounselorDTO {
  private String id;
  private String name;
  private LocalDateTime regDate;
  private String token;
  private Collection<? extends GrantedAuthority> authorities;
  
  public static CounselorDTO readFrom(Counselor c) {
    if (c == null) return null;
    return CounselorDTO.builder()
                          .id(c.getId())
                          .name(c.getName())
                          .regDate(c.getRegDate())
                          .authorities(c.getAuthorities()).build();
  }
}
