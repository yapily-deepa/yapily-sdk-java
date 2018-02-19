package com.acaciaconnect.api.client.model.acacia;

import com.acaciaconnect.api.client.model.HasUUID;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Representation of an application user.
 */
@Data
public class ApplicationUser implements HasUUID {
  private UUID uuid;
  private UUID applicationUuid;
  //External ApplicationUser ID
  private String appUserId;
  @JsonManagedReference
  private List<ConnectedBank> connectedBanks = new ArrayList<>();
}
