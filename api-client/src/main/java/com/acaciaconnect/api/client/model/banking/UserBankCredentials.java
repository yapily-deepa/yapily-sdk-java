package com.acaciaconnect.api.client.model.banking;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.acaciaconnect.api.client.model.acacia.ConnectedBank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString(exclude = "connectedBank")
public class UserBankCredentials {
  UUID uuid;
  UUID userUuid;
  @JsonBackReference
  ConnectedBank connectedBank;
  OAuth2AccessToken bankAuthCredentials;
}
