package com.yapily.api.client.model.yapily;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.yapily.api.client.model.banking.UserBankCredentials;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="applicationUser")
public class ConnectedBank {
    UUID uuid;
    @JsonBackReference
    ApplicationUser applicationUser;
    String bankId;
    Date created;
    Date lastVisited;
    @JsonManagedReference
    List<UserBankCredentials> credentials = new ArrayList<>();
}
