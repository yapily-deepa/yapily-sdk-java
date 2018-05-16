package yapily.examples;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import yapily.api.client.model.Account;
import yapily.api.client.model.ApplicationUser;
import yapily.api.client.model.Consent;
import yapily.api.client.model.Identity;
import yapily.api.client.model.Transaction;
import yapily.sdk.YapilyApi;
import yapily.sdk.services.institutions.AccountsApi;
import yapily.sdk.services.institutions.Auth;
import yapily.sdk.services.institutions.IdentitiesApi;
import yapily.sdk.services.institutions.TransactionsApi;
import yapily.sdk.services.yapily.UsersApi;

/**
 * This example simulates creating and authenticating an institution user, returning normalised data
 * from AccountsApi, TransactionsApi and Identity endpoints. Application credentials must be created and
 * managed in the Yapily Dashboard Application. For demo purposes, the application ID and secret are
 * included as constants.
 */
public class ExampleAccountDetails {

    public static void main(String[] args) {
        System.out.println("Hello Yapily API!");

        String applicationId = Constants.APPLICATION_ID;
        String applicationSecret = Constants.APPLICATION_SECRET;

        // Set access credentials
        System.setProperty(YapilyApi.API_APPLICATION_ID_ENV_NAME, applicationId);
        System.setProperty(YapilyApi.API_APPLICATION_SECRET_ENV_NAME, applicationSecret);

        // Create a user for this application
        final UsersApi usersApi = new UsersApi.Builder().standard().build();
        ApplicationUser appUser = new ApplicationUser();
        appUser.setUuid(UUID.randomUUID().toString());
        final ApplicationUser applicationUser = usersApi.createUser(appUser);
        System.out.println("Created applicationUser with uuid: " + applicationUser.getUuid());

        // Set user and institution id variables
        String institutionId = "bbva-sandbox";
        String userUuid = applicationUser.getUuid();

        // Send applicationUser to authentication for an institution and add a callback with credentials
        final Auth auth = new Auth();
        final URI directUrl = auth.authDirectURL(applicationId, userUuid, institutionId, Constants.CALLBACK_URL, "account");
        if (Desktop.isDesktopSupported()) {
            try (final Scanner reader = new Scanner(System.in)) {
                Desktop.getDesktop().browse(directUrl);

                // --- AFTER AUTHENTICATING, YOU SHOULD BE REDIRECTED
                System.out.println("After completing the authentication, press Enter to continue: [enter]");
                reader.nextLine();

                List<Consent> allConsents = usersApi.listConsents(userUuid);

                System.out.println("**************ACCOUNTS******************");
                System.out.println(allConsents);
                System.out.println("****************************************");

                Consent consent = usersApi.listConsentsForInstitution(userUuid, institutionId).get(0);

                final AccountsApi accountsApi = new AccountsApi.Builder().standard()
                                                                         .withConsentToken(consent.getConsentToken())
                                                                         .build();

                List<Account> accounts = accountsApi.listAccounts();

                // Print accounts
                System.out.println("**************ACCOUNTS******************");
                System.out.println(accounts);
                System.out.println("****************************************");

                final TransactionsApi transactionsApi = new TransactionsApi.Builder().withApplicationCredentials(applicationId, applicationSecret)
                                                                                     .withConsentToken(consent.getConsentToken())
                                                                                     .build();

                List<Transaction> transactions = transactionsApi.listTransactions(accounts.get(0).getId());

                System.out.println("**************TRANSACTIONS**************");
                System.out.println(transactions);
                System.out.println("****************************************");

                final IdentitiesApi identitiesApi = new IdentitiesApi.Builder().withApplicationCredentials(applicationId, applicationSecret)
                                                                               .withConsentToken(consent.getConsentToken())
                                                                               .build();

                Identity identity = identitiesApi.getIdentity();

                System.out.println("**************IDENTITY******************");
                System.out.println(identity);
                System.out.println("****************************************");

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

}
