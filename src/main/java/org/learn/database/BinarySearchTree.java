package org.learn.database;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public void insert(int key, String value) {
        root = insertRec(root, key, value);
    }

    private Node insertRec(Node root, int key, String value) {
        if (root == null) {
            root = new Node(key, value);
            return root;
        }

        if (key < root.getKey()) {
            root.setLeft(insertRec(root.getLeft(), key, value));
        } else if (key > root.getKey()) {
            root.setRight(insertRec(root.getRight(), key, value));
        }
        return root;
    }

    public String search(int key) {
        Node result = searchRec(root, key);
        return result != null ? result.getValue() : null;
    }

    private Node searchRec(Node root, int key) {
        if (root == null || root.getKey() == key) {
            return root;
        }

        if (key < root.getKey()) {
            return searchRec(root.getLeft(), key);
        }
        return searchRec(root.getRight(), key);
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.getKey()) {
            root.setLeft(deleteRec(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(deleteRec(root.getRight(), key));
        } else {
            // Node with only one child or no child
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            Node minNode = minValueNode(root.getRight());
            root.setKey(minNode.getKey());
            root.setValue(minNode.getValue());

            // Delete the inorder successor
            root.setRight(deleteRec(root.getRight(), minNode.getKey()));
        }

        return root;
    }

    private Node minValueNode(Node root) {
        Node current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public void update(int key, String newValue) {
        Node node = searchRec(root, key);
        if (node != null) {
            node.setValue(newValue);
        }
    }

    public List<String> getAll() {
        List<String> result = new ArrayList<>();
        inorderCollect(root, result);
        return result;
    }

    private void inorderCollect(Node root, List<String> result) {
        if (root != null) {
            inorderCollect(root.getLeft(), result);
            result.add(root.getKey() + " -> " + root.getValue());
            inorderCollect(root.getRight(), result);
        }
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.println(root.getKey() + " -> " + root.getValue());
            inorderRec(root.getRight());
        }
    }
}