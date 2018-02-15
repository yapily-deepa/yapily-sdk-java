package com.yapily.api.client.model.banking;

import java.util.List;

import lombok.Data;

@Data
public class Identity {

  private String id;
  private String firstName;
  private String lastName;
  private String gender;
  private String birthdate;
  private String email;
  private String phone;

  private List<IdentityAddress> addresses;

}
