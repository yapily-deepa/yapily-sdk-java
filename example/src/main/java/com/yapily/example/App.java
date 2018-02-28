package com.yapily.example;

import java.util.stream.Collectors;

import com.yapily.sdk.AcaciaApi;
import com.yapily.api.client.model.Bank;
import com.yapily.sdk.services.acacia.Banks;

/**
 * Hello Acacia! This example lists all banks' names retrieved from the Acacia API server using the
 * Acacia SDK
 *
 */
public class App {

  public static void main(String[] args) {

    System.out.println("Hello Acacia API!");

    // Set access credentials
    System.setProperty(AcaciaApi.API_APPLICATION_ID_ENV_NAME, Constants.APPLICATION_ID);
    System.setProperty(AcaciaApi.API_APPLICATION_SECRET_ENV_NAME, Constants.APPLICATION_SECRET);

    System.out.println("Set application credentials as system properties");

    // Create Banks API client
    final Banks banksApi = new Banks();

    // List all banks
    final String banksList = banksApi.listBanks()
                                     .stream()
                                     .map(Bank::getName)
                                     .collect(Collectors.joining(", "));

    System.out.println("Available Banks: " + banksList);

  }

}
