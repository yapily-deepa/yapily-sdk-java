package yapily.examples;

import java.util.stream.Collectors;

import yapily.api.client.model.Institution;
import yapily.sdk.YapilyApi;
import yapily.sdk.services.yapily.Institutions;

/**
 * Hello Yapily! This example lists all institutions' names retrieved from the Yapily API server
 * using the Yapily SDK
 */
public class ExampleGetInstitutions {

    public static void main(String[] args) {

        System.out.println("Hello Yapily API!");

        // Set access credentials
        System.setProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME, Constants.APPLICATION_ID);
        System.setProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME, Constants.APPLICATION_SECRET);

        System.out.println("Set application credentials as system properties");

        // Create Institutions API client
        final Institutions institutionsApi = new Institutions();

        // List all institutions
        final String institutionsList = institutionsApi.listInstitutions()
                                                       .stream()
                                                       .map(Institution::getName)
                                                       .collect(Collectors.joining(", "));

        System.out.println("Available Institutions: " + institutionsList);

    }

}
