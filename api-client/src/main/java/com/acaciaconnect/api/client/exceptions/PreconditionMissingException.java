package com.acaciaconnect.api.client.exceptions;

final public class PreconditionMissingException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  PreconditionMissingException(String description) {
    super(description);
  }

}
