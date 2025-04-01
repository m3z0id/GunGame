package com.m3z0id.gunGame.utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static String getKeyForNthLargestValue(Map<String, Integer> map, int n) {
        if (map == null || map.isEmpty() || n <= 0 || n > map.size()) {
            return "Blank";
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        Map.Entry<String, Integer> nthEntry = sortedEntries.get(n - 1);

        return nthEntry.getKey();
    }
    public static String is2s(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            return "{}";
        }
    }
    public static <T> T[] reverseArray(T[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
        return array;
    }
}
