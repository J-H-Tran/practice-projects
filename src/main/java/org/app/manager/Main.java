package org.app.manager;

import org.app.manager.config.AppConfig;
import org.app.manager.service.PersonService;
import org.app.manager.util.AnalyticsUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        PersonService service = config.personService();

        // Insert initial records
        System.out.println("All records in the database:");
        service.displayRecords();

        // Get a record by key
        System.out.println("Record with key 2: " + service.getRecord(2));

        // Update a record
        service.updateRecord(2, "Zio");
        System.out.println("Record with key 2 after update: " + service.getRecord(2));

        // Delete a record
        service.deleteRecord(2);
        System.out.println("Record with key 2 after deletion: " + service.getRecord(2));

        // Search records by name
        System.out.println("Records containing 'Record': " + service.searchByName("Record"));

        // AnalyticsUtil method calls
        var records = config.database().getAllRecords();
        var occurrences = AnalyticsUtil.countOccurrences(records);
        System.out.println("Occurrences of each name: " + occurrences);

        String mostCommonName = AnalyticsUtil.findMostCommonName(records);
        System.out.println("Most common name: " + mostCommonName);

        var averageNameLength = AnalyticsUtil.calculateAverageNameLength(records);
        System.out.println("Average length of names: " + averageNameLength);

        // Backup records to a file
        try {
            service.backupRecords("backup.txt");
            System.out.println("Records backed up to backup.txt");
        } catch (IOException e) {
            System.err.println("Failed to backup records: " + e.getMessage());
        }

        // Restore records from a file
        try {
            service.restoreRecords("backup.txt");
            System.out.println("Records restored from backup.txt");
        } catch (IOException e) {
            System.err.println("Failed to restore records: " + e.getMessage());
        }

        System.out.println("All records in the database:");
        service.displayRecords();
    }
}
/**
 * TODO:
 * Here are five projects you can implement with your current codebase:
 * 1. DONE **Enhanced Person Management System**:
 *    - Add functionalities to update and delete person records.
 *    - Implement search functionality to find records by name.
 * 2. **Web Application for Person Records**:
 *    - Create a web interface using a framework like Spring Boot.
 *    - Allow users to interact with the person records through a web browser.
 * 3. **RESTful API for Person Records**:
 *    - Develop a RESTful API to perform CRUD operations on person records.
 *    - Use frameworks like Spring Boot to expose endpoints for the database operations.
 * 4. DONE **Person Records Analytics**:
 *    - Implement analytics features to generate reports on the person records.
 *    - Include functionalities like counting records, finding the most common names, etc.
 * 5. DONE **Person Records Backup and Restore**:
 *    - Add functionalities to backup the person records to a file.
 *    - Implement restore functionality to load records from a backup file.
 **/