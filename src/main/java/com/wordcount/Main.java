package com.wordcount;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide file path as command line argument");
            return;
        }
        
        String amount = System.getenv("PRINT_AMOUNT");

        try {
            String filePath = args[0];
            WordCounter wordCounter = new WordCounter();
            LinkedHashMap<String, Long> counter = wordCounter.count(filePath);
            Stream<Map.Entry<String, Long>> stream = amount == null ? counter.entrySet().stream() : counter.entrySet().stream().limit(Integer.parseInt(amount));
            stream.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}