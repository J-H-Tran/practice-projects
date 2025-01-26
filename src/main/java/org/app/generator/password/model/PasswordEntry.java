package org.app.generator.password.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a password entry with the encrypted password and its creation date.
 */
public class PasswordEntry {
    private final String password;
    private final LocalDateTime creationDate;

    /**
     * Constructs a PasswordEntry with the given password and the current date and time as the creation date.
     *
     * @param password the encrypted password
     */
    public PasswordEntry(String password) {
        this.password = password;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Constructs a PasswordEntry with the given password and creation date.
     *
     * @param password the encrypted password
     * @param creationDate the date and time when the password was created
     */
    public PasswordEntry(String password, LocalDateTime creationDate) {
        this.password = password;
        this.creationDate = creationDate;
    }

    /**
     * Returns the encrypted password.
     *
     * @return the encrypted password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the creation date of the password.
     *
     * @return the creation date of the password
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Returns a string representation of the PasswordEntry in the format "password,creationDate".
     *
     * @return a string representation of the PasswordEntry
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return password + "," + creationDate.format(formatter);
    }

    /**
     * Creates a PasswordEntry from a string representation.
     *
     * @param str the string representation of the PasswordEntry in the format "password,creationDate"
     * @return a PasswordEntry object
     */
    public static PasswordEntry fromString(String str) {
        String[] parts = str.split(",");
        String password = parts[0];
        LocalDateTime creationDate = LocalDateTime.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new PasswordEntry(password, creationDate);
    }
}