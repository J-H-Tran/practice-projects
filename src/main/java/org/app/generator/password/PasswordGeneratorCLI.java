package org.app.generator.password;

import org.app.generator.password.model.PasswordEntry;
import org.app.generator.password.service.PasswordGenerator;
import org.app.generator.password.util.PasswordEncryptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.Set;

public class PasswordGeneratorCLI {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {
                System.out.println("""

                        \tWelcome to the PasswordGenerator2000!
                        Please choose an option:""");
                System.out.println("1. Generate password");
                System.out.println("2. Show how many passwords have been generated and list them");
                System.out.println("3. Check for passwords older than 90 days");
                System.out.println("4. Notify expiring passwords");
                System.out.println("5. Retrieve passwords by date range");
                System.out.println("6. Clear password history");
                System.out.println("7. Exit");

                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline character

                switch (choice) {
                    case 1: // Generate password
                        String password = PasswordGenerator.generatePassword();
                        System.out.println("Generated Password: " + password);
                        break;
                    case 2: // Show how many passwords have been generated and list them
                        System.out.println(PasswordGenerator.getPasswordCount() + " Generated Passwords:");
                        PasswordGenerator.getGeneratedPasswordSet().forEach(entry ->
                                System.out.println("Password: " + entry.getPassword() +
                                        ", Created on: " + entry.getCreationDate()));
                        break;
                    case 3: // Check for passwords older than 90 days
                        Set<PasswordEntry> oldPasswords = PasswordGenerator.getPasswordsOlderThan90Days();

                        if (oldPasswords.isEmpty()) {
                            System.out.println("No passwords older than 90 days.");
                        } else {
                            System.out.println("Passwords older than 90 days:");
                            oldPasswords.forEach(entry ->
                                    System.out.println("Password: " + entry.getPassword() +
                                            ", Created on: " + entry.getCreationDate()));
                        }
                        break;
                    case 4: // Notify expiring passwords
                        Set<PasswordEntry> allPasswords = PasswordGenerator.getGeneratedPasswordSet();
                        LocalDateTime now = LocalDateTime.now();
                        boolean found = false;

                        for (PasswordEntry entry : allPasswords) {
                            long daysBetween = ChronoUnit.DAYS.between(entry.getCreationDate(), now);

                            if (daysBetween > 90) {
                                System.out.println("Password: " + entry.getPassword() + " is already expired.");
                                found = true;
                            } else if (daysBetween > 83) {
                                System.out.println("Password: " + entry.getPassword() + " is expiring soon.");
                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println("No passwords older than 83 days.");
                        }
                        break;
                    case 5: // Retrieve passwords by date range
                        System.out.println("Enter start date (yyyy-MM-ddTHH:mm:ss):");
                        String startDateStr = sc.nextLine();

                        System.out.println("Enter end date (yyyy-MM-ddTHH:mm:ss):");
                        String endDateStr = sc.nextLine();

                        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                        LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
                        LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);

                        Set<PasswordEntry> passwordsByDateRange = PasswordGenerator.getPasswordsByDateRange(startDate, endDate);

                        if (passwordsByDateRange.isEmpty()) {
                            System.out.println("No passwords found in the specified date range.");
                        } else {
                            System.out.println("Passwords in the specified date range:");
                            passwordsByDateRange.forEach(entry ->
                                    System.out.println("Password: " + entry.getPassword() +
                                            ", Created on: " + entry.getCreationDate()));
                        }
                        break;
                    case 6: // Clear password history
                        System.out.println("Are you sure you want to delete the password history? (yes/no)");
                        String confirmation = sc.nextLine();

                        if (confirmation.equalsIgnoreCase("yes")) {
                            PasswordGenerator.clearPasswordHistory();
                            System.out.println("Password history cleared.");
                        } else {
                            System.out.println("Password history not cleared.");
                        }
                        break;
                    case 7: // Exit
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    case 8: // Decrypt all passwords, secret option
                        Set<PasswordEntry> allPasswordsToDecrypt = PasswordGenerator.getGeneratedPasswordSet();

                        if (allPasswordsToDecrypt.isEmpty()) {
                            System.out.println("No passwords have been generated.");
                        } else {
                            System.out.println("Decrypted Passwords:");
                            allPasswordsToDecrypt.forEach(entry -> {
                                try {
                                    String decryptedPassword = PasswordEncryptor.decrypt(entry.getPassword());
                                    System.out.println("Password: " + decryptedPassword +
                                            ", Created on: " + entry.getCreationDate());
                                } catch (RuntimeException e) {
                                    System.out.println(
                                            "Error decrypting password for entry created on: " + entry.getCreationDate()
                                    );
                                }
                            });
                        }
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}