package org.learn.database;

import org.learn.util.RecordUtil;

import java.util.Map;

public class Database {
    private final BinarySearchTree bst;

    public Database() {
        bst = new BinarySearchTree();
    }

    public void insertRecord(int key, String value) {
        bst.insert(key, value);
    }

    public String getRecord(int key) {
        return bst.search(key);
    }

    public void updateRecord(int key, String value) {
        bst.update(key, value);
    }

    public void deleteRecord(int key) {
        bst.delete(key);
    }

    public Map<Integer, String> getAllRecords() {
        return RecordUtil.convertListToMap(bst.getAll());
    }

    public void displayRecords() {
        bst.inorder();
    }
}