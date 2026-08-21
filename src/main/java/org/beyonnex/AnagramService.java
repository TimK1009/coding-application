package org.beyonnex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramService {

    public AnagramService() {}

    private Map<String, List<String>> anagramIndex = new HashMap<>();

    public boolean runAnagramCheck(final String input1, final String input2) {
        if (containsDigitsOrSpecialChars(input1) || containsDigitsOrSpecialChars(input2)) {
            throw new IllegalArgumentException("Input must not contain digits or special characters.");
        }
        final boolean result = isAnagram(input1, input2);

        if (result) {
            addToIndex(input1);
            addToIndex(input2);
        }
        return result;
    }

    public List<String> runAnagramLookup(final String input) {
        if (containsDigitsOrSpecialChars(input)) {
            throw new IllegalArgumentException("Input must not contain digits or special characters.");
        }
        return findPastAnagrams(input);
    }

    private List<String> findPastAnagrams(final String input) {
        final String key = sortedKey(input);
        final List<String> matches = anagramIndex.getOrDefault(key, new ArrayList<>());
        return matches.stream()
                .filter(knownAnagram -> !knownAnagram.equalsIgnoreCase(input))
                .toList();
    }

    private boolean isAnagram(final String input1, final String input2) {
        final char[] sanitizedInput1 = sanitize(input1).toCharArray();
        final char[] sanitizedInput2 = sanitize(input2).toCharArray();
        Arrays.sort(sanitizedInput1);
        Arrays.sort(sanitizedInput2);
        return Arrays.equals(sanitizedInput1, sanitizedInput2);
    }

    /*
     * Filter for digits and special characters, except apostrophe.
     * */
    private boolean containsDigitsOrSpecialChars(final String input) {
        return input.chars().anyMatch(c -> !Character.isLetter(c) && !Character.isWhitespace(c) && c != '\'');
    }

    private void addToIndex(final String input) {
        final String key = sortedKey(input);
        anagramIndex.computeIfAbsent(key, k -> new ArrayList<>());
        final List<String> entries = anagramIndex.get(key);
        if (entries.stream().noneMatch(e -> e.equalsIgnoreCase(input))) {
            entries.add(input);
        }
    }

    private String sortedKey(final String input) {
        final char[] chars = sanitize(input).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private String sanitize(final String input) {
        return input.toLowerCase().replaceAll("\\s+", "");
    }

    /* Test helper method */
    public void setAnagramIndex(final Map<String, List<String>> anagramIndex) {
        this.anagramIndex = anagramIndex;
    }

    public void clearAnagramIndex() {
        this.anagramIndex = new HashMap<>();
    }

}
