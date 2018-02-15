package com.yapily.api.client.exceptions;

import java.util.NoSuchElementException;

final public class NotFoundException extends NoSuchElementException {
  private static final long serialVersionUID = 1L;

  private final Object entity;

  protected NotFoundException(final Object entity) {
    this.entity = entity;
  }

  Object getEntity() {
    return entity;
  }

}
