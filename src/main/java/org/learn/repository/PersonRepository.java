package org.learn.repository;

import org.learn.database.Database;
import org.learn.datasource.PersonDataSource;

import java.util.Map;
import java.util.stream.Collectors;

public class PersonRepository {
    private final Database database;
    private final PersonDataSource dataSource;

    public PersonRepository(Database database, PersonDataSource dataSource) {
        this.database = database;
        this.dataSource = dataSource;
        loadData();
    }

    private void loadData() {
        dataSource
                .getData()
                .forEach(database::insertRecord);
    }

    public String getRecord(int key) {
        return database.getRecord(key);
    }

    public void updateRecord(int key, String value) {
        database.updateRecord(key, value);
    }

    public void deleteRecord(int key) {
        database.deleteRecord(key);
    }

    public Map<Integer, String> searchByName(String name) {
        return database.getAllRecords().entrySet().stream()
                .filter(entry -> entry.getValue().contains(name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void displayRecords() {
        database.displayRecords();
    }

    public Map<Integer, String> getAllRecords() {
        return database.getAllRecords();
    }
}