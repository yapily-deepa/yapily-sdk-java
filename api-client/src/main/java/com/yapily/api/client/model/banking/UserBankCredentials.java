package com.yapily.api.client.model.banking;

import java.util.UUID;

import com.yapily.api.client.model.yapily.ConnectedBank;
import com.yapily.api.client.model.yapily.OAuth2AccessToken;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "connectedBank")
public class UserBankCredentials {
  UUID uuid;
  UUID userUuid;
  @JsonBackReference
  ConnectedBank connectedBank;
  OAuth2AccessToken bankAuthCredentials;
}
