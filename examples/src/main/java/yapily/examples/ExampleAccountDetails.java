package yapily.examples;

import yapily.api.client.model.ApplicationUser;
import yapily.api.client.model.Account;
import yapily.api.client.model.Identity;
import yapily.api.client.model.Transaction;
import yapily.sdk.YapilyApi;
import yapily.sdk.services.yapily.Users;
import yapily.sdk.services.banking.Accounts;
import yapily.sdk.services.banking.Auth;
import yapily.sdk.services.banking.Identities;
import yapily.sdk.services.banking.Transactions;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This example simulates creating and authenticating a bank user, returning
 * normalised data from Accounts, Transactions and Identity endpoints.
 * Application credentials must be created and managed in the Yapily Dashboard Application.
 * For demo purposes, the application ID and secret are included as constants.
 */
public class ExampleAccountDetails {

    public static void main(String[] args) {
        System.out.println("Hello Yapily API!");

        UUID applicationId = UUID.fromString(Constants.APPLICATION_ID);
        String applicationSecret = Constants.APPLICATION_SECRET;

        // Set access credentials
        System.setProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME, applicationId.toString());
        System.setProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME, applicationSecret);

        // Create a user for this application
        final Users usersApi = new Users();
        final ApplicationUser applicationUser = usersApi.createUser(UUID.randomUUID().toString());
        System.out.println("Created applicationUser with uuid: " + applicationUser.getUuid());

        // Set user and bank id variables
        String bankId = "bbva";
        String userUuid = applicationUser.getUuid();

        // Send applicationUser to authentication for a bank and add a callback with credentials
        final Auth auth = new Auth();
        final URI directUrl = auth.authDirectURL(applicationId, userUuid, bankId, Constants.CALLBACK_URL, "account");
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(directUrl);

                // --- AFTER AUTHENTICATING, YOU SHOULD BE REDIRECTED

                final Scanner reader = new Scanner(System.in);
                System.out.println("After completing the authentication, press Enter to continue: [enter]");
                reader.nextLine();

                // Print out user details

                final Accounts accountsApi = new Accounts();
                List<Account> accounts = accountsApi.listAccounts(userUuid, bankId);

                System.out.println("**************ACCOUNTS******************");
                System.out.println(accounts);
                System.out.println("****************************************");

                final Transactions transactionsApi = new Transactions();
                List<Transaction> transactions = transactionsApi.listTransactions(userUuid, accounts.get(0).getId(), bankId);

                System.out.println("**************TRANSACTIONS**************");
                System.out.println(transactions);
                System.out.println("****************************************");

                final Identities identitiesApi = new Identities();
                Identity identity = identitiesApi.getIdentity(userUuid, bankId);

                System.out.println("**************IDENTITY******************");
                System.out.println(identity);
                System.out.println("****************************************");

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}
