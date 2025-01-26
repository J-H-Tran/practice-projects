package org.app.generator.password.util;

import org.app.generator.password.model.PasswordEntry;
import org.app.generator.password.service.PasswordGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class PasswordRetriever {
    public static Set<PasswordEntry> getPasswordsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return PasswordGenerator.getGeneratedPasswordSet().stream()
                .filter(entry -> !entry.getCreationDate().isBefore(startDate) && !entry.getCreationDate().isAfter(endDate))
                .collect(Collectors.toSet());
    }
}
