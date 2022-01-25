package com.cs.question.api.common.config.exception;

public class CustomException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private int code = -1;

  public CustomException() {
    super();
  }

  public CustomException(int code, String message) {
    super(message);
    this.code = code;
  }

  public CustomException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public CustomException(Throwable cause) {
    super(cause);
  }

  public int getCode() {
    return this.code;
  }
}
