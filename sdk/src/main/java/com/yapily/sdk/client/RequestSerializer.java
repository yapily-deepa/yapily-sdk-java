package com.yapily.sdk.client;

import java.io.IOException;

import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RequestSerializer {

  private Object object;

  public String getString(Object object) throws IOException {
    this.object = object;
    final ObjectMapper objectMapper = new ObjectMapper();
    final String result = objectMapper.writeValueAsString(object);
    return result;
  }

  public String getMimeType() {
    if(object!=null && object instanceof String) return ContentType.TEXT_PLAIN.getMimeType();
    return ContentType.APPLICATION_JSON.getMimeType();
  }

}
