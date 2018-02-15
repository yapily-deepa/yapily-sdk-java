package com.yapily.api.client.exceptions;

final public class PermissionDeniedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  PermissionDeniedException(String description) {
    super(description);
  }

}
