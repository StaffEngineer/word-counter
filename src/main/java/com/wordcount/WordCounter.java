package com.wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounter {

    public LinkedHashMap<String, Long> count(String filePath) {
        Map<String, Long> words = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int charCode;
            StringBuilder tokenBuilder = new StringBuilder();

            while ((charCode = reader.read()) != -1) {
                char currentChar = (char) charCode;

                if (!isWhitespace(currentChar)) {
                    tokenBuilder.append(currentChar);
                } else {
                    processToken(words, tokenBuilder.toString());
                    tokenBuilder.setLength(0);
                }
            }

            // Process the last token if any
            if (tokenBuilder.length() > 0) {
                processToken(words, tokenBuilder.toString());
            }

            return sort(words);
        } catch (IOException e) {
            // Handle the exception according to your use case
            e.printStackTrace();
        }
        return null;
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f';
    }

    private void processToken(Map<String, Long> words, String token) {
        if (!token.isEmpty()) { // Skip empty tokens
            String word = token.toLowerCase();
            words.put(word, words.getOrDefault(word, 0L) + 1);
        }
    }

    private static LinkedHashMap<String, Long> sort(Map<String, Long> words) {
        LinkedHashMap<String, Long> sortedWords = new LinkedHashMap<>();
        words.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(x -> sortedWords.put(x.getKey(), x.getValue()));
        return sortedWords;
    }
}
