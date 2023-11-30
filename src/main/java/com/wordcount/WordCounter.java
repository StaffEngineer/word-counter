package com.wordcount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WordCounter {
    private final File file;

    public WordCounter(File file) {
        this.file = file;
    }

    public LinkedHashMap<String, Long> count() {
        Map<String, Long> words = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken().toLowerCase();
                    words.put(word, words.getOrDefault(word, 0l) + 1);
                }
                line = reader.readLine();
            }
            return sort(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
