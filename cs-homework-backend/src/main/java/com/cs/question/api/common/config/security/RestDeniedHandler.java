package com.cs.question.api.common.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.cs.question.api.common.util.ResponseWriter;

@Component
public class RestDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ade)
      throws IOException, ServletException {
    ResponseWriter.write(response, "유효하지만 권한이 없는 사용자", HttpServletResponse.SC_FORBIDDEN, null);
  }

}