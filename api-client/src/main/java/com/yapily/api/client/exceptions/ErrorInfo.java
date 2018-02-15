package com.yapily.api.client.exceptions;

public class ErrorInfo {

  public final String url;
  public final String exception;

  public ErrorInfo(String url, Exception ex) {
    this.url = url;
    exception = ex.getLocalizedMessage();
  }

  @Override
  public String toString() {
    return "ErrorInfo [url=" + url + ", exception=" + exception + "]";
  }
}
