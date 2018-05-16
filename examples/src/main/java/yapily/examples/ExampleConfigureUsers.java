package yapily.examples;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import yapily.api.client.model.ApplicationUser;
import yapily.sdk.YapilyApi;
import yapily.sdk.services.yapily.UsersApi;

/**
 * This example demonstrates how to create and retrieve users using your application credentials.
 * Application credentials must be created and managed in the Yapily Dashboard Application. For demo
 * purposes, the application ID and secret are included as constants.
 */
public class ExampleConfigureUsers {

    public static void main(String[] args) {

        System.out.println("Hello Yapily API!");

        UUID applicationId = UUID.fromString(Constants.APPLICATION_ID);
        String applicationSecret = Constants.APPLICATION_SECRET;

        // Set access credentials
        System.setProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME, applicationId.toString());
        System.setProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME, applicationSecret);

        System.out.println("Set application credentials as system properties");

        // Create users for this application
        final UsersApi usersApi = new UsersApi.Builder().standard().build();

        usersApi.createUser(newApplicationUser());
        usersApi.createUser(newApplicationUser());
        final ApplicationUser applicationUserC = usersApi.createUser(newApplicationUser());

        System.out.println("Created users");

        List<ApplicationUser> users = usersApi.listUsers();
        System.out.println(users);

        // Retrieve the first user from the api
        String userUuid = users.get(0).getUuid();
        ApplicationUser applicationUser = usersApi.getUser(userUuid);

        System.out.println(applicationUser);

        // List users for this application
        final String recordsList = users.stream()
                                        .map(ApplicationUser::toString)
                                        .collect(Collectors.joining(System.lineSeparator()));

        System.out.println("List of users:");
        System.out.println(recordsList);

        // Change user details for one of the application users
        applicationUserC.setAppUserId("UserC");

        usersApi.updateUser(applicationId, applicationUserC);
        System.out.println("Updated applicationUserC user ID to UserC: " + applicationUserC.toString());

        // List users for this application
        final String recordsListUpdated = users.stream()
                                               .map(ApplicationUser::toString)
                                               .collect(Collectors.joining(System.lineSeparator()));

        System.out.println("List updated user:");
        System.out.println(recordsListUpdated);

    }

    private static ApplicationUser newApplicationUser() {
        ApplicationUser user = new ApplicationUser();
        user.setUuid(UUID.randomUUID().toString());
        return user;
    }

}
