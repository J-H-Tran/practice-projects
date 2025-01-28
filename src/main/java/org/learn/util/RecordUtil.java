package org.learn.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordUtil {

    /**
     * Converts a list of records in the format "key -> value" to a map.
     *
     * @param records a list of strings where each string is in the format "key -> value"
     * @return a map where the keys are integers parsed from the input strings and the values are the corresponding strings
     */
    public static Map<Integer, String> convertListToMap(List<String> records) {
        return records.stream()
                .collect(Collectors.toMap(
                    record -> Integer.parseInt(record.split(" -> ")[0]),
                    record -> record.split(" -> ")[1]
                ));
    }
}
