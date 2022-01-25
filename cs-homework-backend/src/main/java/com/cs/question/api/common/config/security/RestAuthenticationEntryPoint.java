package com.cs.question.api.common.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.cs.question.api.common.util.ResponseWriter;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
    ResponseWriter.write(response, "유효하지 않은 사용자의 접근", HttpServletResponse.SC_UNAUTHORIZED, null);
  }
}