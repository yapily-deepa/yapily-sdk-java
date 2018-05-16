package yapily.examples;

import java.util.List;
import java.util.stream.Collectors;

import yapily.api.client.model.Institution;
import yapily.sdk.YapilyApi;
import yapily.sdk.services.yapily.InstitutionsApi;

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

        // Create InstitutionsApi API client
        final InstitutionsApi institutionsApi = new InstitutionsApi.Builder().standard().build();

        // List all institutions
        List<Institution> institutions = institutionsApi.listInstitutions();

        final String institutionsList = institutions.stream()
                                                    .map(Institution::getName)
                                                    .collect(Collectors.joining(", "));

        System.out.println("Available InstitutionsApi: " + institutionsList);

        // Retrieve one institution
        String institutionId = institutions.get(0).getId();

        Institution institution = institutionsApi.getInstitution(institutionId);

        System.out.println("Retrieved institution " + institution.getId());
    }

}
