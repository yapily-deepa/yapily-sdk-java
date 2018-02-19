package com.acaciaconnect.api.client.model.acacia;

import com.acaciaconnect.api.client.model.banking.UserBankCredentials;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
