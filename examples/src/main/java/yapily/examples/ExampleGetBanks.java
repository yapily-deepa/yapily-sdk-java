package yapily.examples;

import java.util.stream.Collectors;

import yapily.sdk.YapilyApi;
import yapily.api.client.model.Bank;
import yapily.sdk.services.yapily.Banks;

/**
 * Hello Yapily! This example lists all banks' names retrieved from the Yapily API server using the
 * Yapily SDK
 */
public class ExampleGetBanks {

    public static void main(String[] args) {

        System.out.println("Hello Yapily API!");

        // Set access credentials
        System.setProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME, Constants.APPLICATION_ID);
        System.setProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME, Constants.APPLICATION_SECRET);

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
