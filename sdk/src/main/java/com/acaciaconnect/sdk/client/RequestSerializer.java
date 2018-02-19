package com.acaciaconnect.sdk.client;

import java.io.IOException;

import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestSerializer {

  public String getString(Object object) throws IOException {
    final ObjectMapper objectMapper = new ObjectMapper();
    final String result = objectMapper.writeValueAsString(object);
    return result;
  }

  public String getMimeType() {
    return ContentType.APPLICATION_JSON.getMimeType();
  }

}
