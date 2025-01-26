package org.app.manager.datasource;

import java.util.HashMap;
import java.util.Map;

public class PersonDataSource {
    private final Map<Integer, String> data;

    public PersonDataSource() {
        data = new HashMap<>();
        data.put(1, "Alice");
        data.put(2, "Walter");
        data.put(3, "Record");
        data.put(4, "James");
        data.put(5, "Record");
        data.put(6, "Record");
    }

    public Map<Integer, String> getData() {
        return data;
    }
}