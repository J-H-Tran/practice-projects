package org.app.generator.password.service;

import org.app.generator.password.model.PasswordEntry;
import org.app.generator.password.util.PasswordEncryptor;
import org.app.generator.password.util.PasswordRetriever;
import org.app.generator.password.util.PasswordValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Service class for generating, storing, and managing passwords.
 */
public class PasswordGenerator {
    private static final Logger LOGGER = Logger.getLogger(PasswordGenerator.class.getName());
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%&*()-_+=<>?";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final int PASSWORD_LENGTH = 16;
    private static final Set<PasswordEntry> generatedPasswordSet = Collections.synchronizedSet(new HashSet<>());
    private static final Path PASSWORD_FILE = Paths.get("passwords.csv");

    // Static block to load passwords from file when the class is loaded
    static {
        loadPasswordsFromFile();
    }

    /**
     * Generates a new password, encrypts it, and stores it.
     *
     * @return the encrypted password
     */
    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        String passwordStr;
        boolean isDuplicate;

        do {
            StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                int index = random.nextInt(ALL_CHARACTERS.length());
                password.append(ALL_CHARACTERS.charAt(index));
            }

            final String finalPasswordStr = password.toString();
            passwordStr = finalPasswordStr;
            isDuplicate = generatedPasswordSet.stream()
                    .anyMatch(entry -> entry.getPassword().equals(finalPasswordStr));

        } while (isDuplicate || !PasswordValidator.isValid(passwordStr));

        String encryptedPassword = PasswordEncryptor.encrypt(passwordStr);
        PasswordEntry passwordEntry = new PasswordEntry(encryptedPassword);
        generatedPasswordSet.add(passwordEntry);
        savePasswordsToFile();
        return passwordEntry.getPassword();
    }

    /**
     * Clears the history of generated passwords.
     */
    public static void clearPasswordHistory() {
        generatedPasswordSet.clear();
        savePasswordsToFile();
    }

    /**
     * Gets the count of generated passwords.
     *
     * @return the number of generated passwords
     */
    public static int getPasswordCount() {
        return generatedPasswordSet.size();
    }

    /**
     * Retrieves all generated passwords.
     *
     * @return a set of all generated passwords
     */
    public static Set<PasswordEntry> getGeneratedPasswordSet() {
        return new HashSet<>(generatedPasswordSet);
    }

    /**
     * Retrieves passwords that are older than 90 days.
     *
     * @return a set of passwords older than 90 days
     */
    public static Set<PasswordEntry> getPasswordsOlderThan90Days() {
        LocalDateTime now = LocalDateTime.now();
        return generatedPasswordSet.stream()
                .filter(entry -> ChronoUnit.DAYS.between(entry.getCreationDate(), now) > 90)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves passwords within a specified date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a set of passwords within the specified date range
     */
    public static Set<PasswordEntry> getPasswordsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return PasswordRetriever.getPasswordsByDateRange(startDate, endDate);
    }

    /**
     * Loads passwords from the CSV file into the generatedPasswords set.
     */
    private static void loadPasswordsFromFile() {
        if (Files.exists(PASSWORD_FILE)) {
            try {
                List<String> lines = Files.readAllLines(PASSWORD_FILE);
                generatedPasswordSet.addAll(lines.stream()
                        .map(PasswordEntry::fromString)
                        .collect(Collectors.toSet()));
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading passwords from file", e);
            }
        }
    }

    /**
     * Saves the generated passwords to the CSV file.
     */
    private static void savePasswordsToFile() {
        try {
            List<String> lines = generatedPasswordSet.stream()
                    .map(PasswordEntry::toString)
                    .collect(Collectors.toList());
            Files.write(PASSWORD_FILE, lines);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing passwords to file", e);
        }
    }
}