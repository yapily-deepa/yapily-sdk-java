package com.yapily.api.client.model.banking;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Account {

  String id;
  String type;
  String description;
  BigDecimal balance;
  String currency;

}
