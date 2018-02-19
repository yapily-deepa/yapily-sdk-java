package com.acaciaconnect.api.client.model.acacia;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Bank {

  private String id;
  private String name;
  private String fullName;
  private Set<Country> countries = new HashSet<>();
  private boolean active;
  private BankTypeEnum bankType;
  private boolean implemented;
  //TODO: Credentials Type field?
}
