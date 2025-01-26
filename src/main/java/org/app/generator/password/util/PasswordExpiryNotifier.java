package org.app.generator.password.util;

import org.app.generator.password.model.PasswordEntry;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class PasswordExpiryNotifier {
    public static void notifyExpiringPasswords(Set<PasswordEntry> passwords) {
        LocalDateTime now = LocalDateTime.now();

        passwords.stream()
                .filter(entry -> {
                    long daysBetween = ChronoUnit.DAYS.between(entry.getCreationDate(), now);
                    return daysBetween > 83 && daysBetween <= 90;
                })
                .forEach(entry -> System.out.println("Password: " + entry.getPassword() + " is expiring soon."));
    }
}
