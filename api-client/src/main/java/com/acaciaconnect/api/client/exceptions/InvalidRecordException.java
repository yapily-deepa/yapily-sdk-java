package com.acaciaconnect.api.client.exceptions;

final public class InvalidRecordException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final Object entity;

  protected InvalidRecordException(final Object entity) {
    this.entity = entity;
  }

  protected InvalidRecordException(String description, final Object entity) {
    super(description);
    this.entity = entity;
  }

  Object getEntity() {
    return entity;
  }

}
