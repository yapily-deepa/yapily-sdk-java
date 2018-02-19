package com.acaciaconnect.api.client.model.banking;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Transaction {

  String id;
  Date date;
  BigDecimal amount;
  String currency;
  String description;

}
