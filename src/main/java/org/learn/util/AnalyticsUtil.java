package org.learn.util;

import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsUtil {

    /**
     * Counts the occurrences of each name in the provided list of records.
     *
     * @param records a list of strings representing the records
     * @return a map where the keys are the names and the values are the counts of occurrences of each name
     */
    public static Map<String, Long> countOccurrences(Map<Integer, String> records) {
        return records.values().stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }

    /**
     * Finds the most common name in the provided list of records.
     *
     * @param records a list of strings representing the records
     * @return the most common name in the list, or null if the list is empty
     */
    public static String findMostCommonName(Map<Integer, String> records) {
        return records.values().stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Calculates the average length of names in the provided list of records.
     *
     * @param records a list of strings representing the records
     * @return the average length of the names, or 0.0 if the list is empty
     */
    public static double calculateAverageNameLength(Map<Integer, String> records) {
        return records.values().stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
    }
}
