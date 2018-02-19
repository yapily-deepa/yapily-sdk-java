package com.acaciaconnect.api.client.model.banking;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class IdentityAddress {

  private List<String> addressLines = new ArrayList<>();
  private String city;
  private String postalCode;
  private String country;

}
