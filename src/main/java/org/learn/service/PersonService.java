package org.learn.service;

import org.learn.repository.PersonRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public String getRecord(int key) {
        return repository.getRecord(key);
    }

    public void updateRecord(int key, String value) {
        repository.updateRecord(key, value);
    }

    public void deleteRecord(int key) {
        repository.deleteRecord(key);
    }

    public Map<Integer, String> searchByName(String name) {
        return repository.searchByName(name);
    }

    public void displayRecords() {
        repository.displayRecords();
    }

    public void backupRecords(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (var entry : repository.getAllRecords().entrySet()) {
                writer.write(entry.getKey() + " -> " + entry.getValue());
                writer.newLine();
            }
        }
    }

    public void restoreRecords(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" -> ");
                var key = Integer.parseInt(parts[0]);
                String value = parts[1];
                repository.updateRecord(key, value);
            }
        }
    }
}