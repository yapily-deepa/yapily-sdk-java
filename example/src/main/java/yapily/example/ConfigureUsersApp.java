package yapily.example;

import yapily.sdk.YapilyApi;
import yapily.api.client.model.ApplicationUser;
import yapily.sdk.services.yapily.Users;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This example demonstrates how to create and retrieve users using your application credentials.
 * Application credentials must be created and managed in the Yapily Dashboard Application.
 * For demo purposes, the application ID and secret are included as constants.
 */
public class ConfigureUsersApp {

    public static void main(String[] args) {

        System.out.println("Hello Yapily API!");

        UUID applicationId = UUID.fromString(Constants.APPLICATION_ID);
        String applicationSecret = Constants.APPLICATION_SECRET;

        // Set access credentials
        System.setProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME, applicationId.toString());
        System.setProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME, applicationSecret);

        System.out.println("Set application credentials as system properties");

        // Create users for this application
        final Users usersApi = new Users();
        usersApi.createUser(UUID.randomUUID().toString());
        usersApi.createUser(UUID.randomUUID().toString());
        final ApplicationUser applicationUserC = usersApi.createUser(UUID.randomUUID().toString());

        System.out.println("Created users");

        System.out.println(usersApi.listUsers(applicationId));

        // List users for this application
        final String recordsList = usersApi.listUsers(applicationId)
                                           .stream()
                                           .map(ApplicationUser::toString)
                                           .collect(Collectors.joining(System.lineSeparator()));

        System.out.println("List of users:");
        System.out.println(recordsList);

        // Change user details for one of the application users
        applicationUserC.setAppUserId("UserC");

        usersApi.updateUser(applicationId, applicationUserC);
        System.out.println("Updated applicationUserC user ID to UserC: " + applicationUserC.toString());

        // List users for this application
        final String recordsListUpdated = usersApi.listUsers(applicationId)
                                                  .stream()
                                                  .map(ApplicationUser::toString)
                                                  .collect(Collectors.joining(System.lineSeparator()));

        System.out.println("List updated user:");
        System.out.println(recordsListUpdated);

    }

}
