package com.acaciaconnect.api.client.exceptions;

final public class InvalidRequestException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  protected InvalidRequestException(String description) {
    super(description);
  }

}
