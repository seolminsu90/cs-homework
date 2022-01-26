package com.cs.question.api.common.config.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cs.question.api.common.model.ApiResponse;

@RestControllerAdvice
public class ControllerAdviceConfig {
  
  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ApiResponse<Void>> customException(CustomException e) {
    ApiResponse<Void> response = new ApiResponse<>(e.getCode(), e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ApiResponse<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
    
    BindingResult bindingResult = e.getBindingResult();
    
    String errorMsg = e.getMessage();
    StringBuilder sb = new StringBuilder();
    FieldError fieldError;
    if (!bindingResult.getFieldErrors().isEmpty()) {
      fieldError = bindingResult.getFieldErrors().get(0);
      
      sb.append("[");
      sb.append(fieldError.getField());
      sb.append("](은)는 ");
      sb.append(fieldError.getDefaultMessage());
      sb.append(" 입력된 값: [");
      sb.append(fieldError.getRejectedValue());
      sb.append("]");
      errorMsg = sb.toString();
    }
    
    if (errorMsg.length() > 1000) {
      errorMsg = errorMsg.substring(0, 1000);
    }
    
    ApiResponse<Void> response = new ApiResponse<>(400, errorMsg);
    
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(AccessDeniedException.class)
  protected ResponseEntity<ApiResponse<Void>> badCredentialsException(AccessDeniedException e) {
    ApiResponse<Void> response = new ApiResponse<>(403, e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }
  
  @ExceptionHandler(BadCredentialsException.class)
  protected ResponseEntity<ApiResponse<Void>> badCredentialsException(BadCredentialsException e) {
    ApiResponse<Void> response = new ApiResponse<>(401, e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }
  
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiResponse<Void>> anyException(Exception e) {
    e.printStackTrace();
    ApiResponse<Void> response = new ApiResponse<>(500, e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
