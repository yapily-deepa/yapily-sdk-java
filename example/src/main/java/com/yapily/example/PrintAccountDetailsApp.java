package com.yapily.example;

import com.yapily.sdk.AcaciaApi;
import com.yapily.api.client.model.ApplicationUser;
import com.yapily.api.client.model.Account;
import com.yapily.api.client.model.Identity;
import com.yapily.api.client.model.Transaction;
import com.yapily.sdk.services.acacia.Banks;
import com.yapily.sdk.services.acacia.Users;
import com.yapily.sdk.services.banking.Accounts;
import com.yapily.sdk.services.banking.Auth;
import com.yapily.sdk.services.banking.Identities;
import com.yapily.sdk.services.banking.Transactions;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This example simulates creating and authenticating a bank user, returning
 * normalised data from Accounts, Transactions and Identity endpoints.
 * Application credentials must be created and managed in the Acacia Dashboard Application.
 * For demo purposes, the application ID and secret are included as constants.
 */
public class PrintAccountDetailsApp {
    public static void main(String[] args) {
        System.out.println("Hello Acacia API!");

        UUID applicationId = UUID.fromString(Constants.APPLICATION_ID);
        String applicationSecret = Constants.APPLICATION_SECRET;

        // Set access credentials
        System.setProperty(AcaciaApi.API_APPLICATION_ID_ENV_NAME, applicationId.toString());
        System.setProperty(AcaciaApi.API_APPLICATION_SECRET_ENV_NAME, applicationSecret);

        // Create a user for this application
        final Users usersApi = new Users();
        final ApplicationUser applicationUser = usersApi.createUser(UUID.randomUUID().toString());
        System.out.println("Created applicationUser with uuid: " + applicationUser.getUuid());
        Banks banks = new Banks();

        // Set user and bank id variables
        // banks.listBanks().stream().filter(bank -> bank.getId().equals("bbva")).findFirst().get().getId();
        String bankId = "bbva";
        String userUUID = applicationUser.getUuid();

        // Send applicationUser to authentication for a bank and add a callback with credentials
        final Auth auth = new Auth();
        final URI directUrl = auth.authDirectURL(applicationId, userUUID, bankId, Constants.CALLBACK_URL, "account");
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(directUrl);

                // --- AFTER AUTHENTICATING, YOU SHOULD BE REDIRECTED

                final Scanner reader = new Scanner(System.in);
                System.out.println("After completing the authentication, press Enter to continue: [enter]");
                reader.nextLine();

                // Print out user details

                final Accounts accountsApi = new Accounts();
                List<Account> accounts = accountsApi.listAccounts(userUUID, bankId);

                System.out.println("**************ACCOUNTS******************");
                System.out.println(accounts);
                System.out.println("****************************************");

                final Transactions transactionsApi = new Transactions();
                List<Transaction> transactions = transactionsApi.listTransactions(userUUID, accounts.get(0).getId(), bankId);

                System.out.println("**************TRANSACTIONS**************");
                System.out.println(transactions);
                System.out.println("****************************************");

                Identities identitiesApi = new Identities();
                Identity identity = identitiesApi.getIdentity(userUUID, bankId);

                System.out.println("**************IDENTITY******************");
                System.out.println(identity);
                System.out.println("****************************************");

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }


    }
}
