package com.acaciaconnect.api.client.exceptions;

final public class UnauthorizedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  UnauthorizedException(String description) {
    super(description);
  }

}
