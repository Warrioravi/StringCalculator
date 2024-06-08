package org.example.tddKata1;

import java.util.ArrayList;

public class StringCalculator {
    public static int add(String input) {
            if (input.trim().isEmpty()) {
                return 0;
            }
            // Replace reserved regex characters in the input to avoid issues while generating the regex
            input = replaceReservedCharacters(input, "-");
            String regex = "[,\n]";  // Default regex to handle newline and comma as delimiters

            if (input.length() > 2 && input.startsWith("//")) {  // Check for custom delimiter
                // Extract the custom delimiter and form regex
                String delimiterPart = input.substring(2, input.indexOf('\n'));
                regex = getRegex(delimiterPart);
                // Updating the input to exclude the custom delimiter declaration in the front
                input = input.substring(input.indexOf('\n') + 1);
            }
            String[] arr = input.split(regex);
            return findSum(arr);
    }

    public static int findSum(String[] arr) {
            int sum = 0;
            ArrayList<String> negatives = new ArrayList<>();
            for (String str : arr) {
                if (!str.isEmpty()) {
                    int currNum = Integer.parseInt(str);
                    // Check if the number is negative or exceeds 1000
                    if (currNum < 0) {
                        negatives.add(str);
                    } else if (currNum <= 1000) {
                        sum += currNum;
                    }
                }
            }

            // Throw an exception if there are negative numbers
            if (!negatives.isEmpty()) {
                String negativeNumbers = String.join(",", negatives);
                throw new RuntimeException("Negative numbers not allowed: "+ negativeNumbers);
            }

            return sum;
    }

        public static String replaceReservedCharacters(String text, String newDelimiter) {
            // Regex pattern for reserved characters excluding brackets
            String reservedCharsPattern = "[.*+?^$|]";
            // Replace reserved characters with the new delimiter
            return text.replaceAll(reservedCharsPattern, newDelimiter);
        }

        public static String getRegex(String str) {
        // In case of only a single delimiter of a single character
        if (str.length() == 1) {
            return "[" + str + "\n]";
        }
        // In case of multiple delimiters of any length
        // [a][b][c] -> removing front and last bracket -> "a][b][c" -> split on basis of "][" -> a, b, c -> form regex using "|" or operator -> (a|b|c)
        String[] delimiters = str.substring(1, str.length() - 1).split("]\\[");
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("(");
        for (int i = 0; i < delimiters.length; i++) {
            regexBuilder.append(delimiters[i]);
            if (i < delimiters.length - 1) {
                regexBuilder.append("|");
            }
        }
        regexBuilder.append("|\n)");
        return regexBuilder.toString();
      }
}

