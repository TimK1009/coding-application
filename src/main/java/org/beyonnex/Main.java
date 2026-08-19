package org.beyonnex;

import java.util.List;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        final AnagramService anagramService = new AnagramService();
        final Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("Choose a feature:");
            System.out.println("1 - Check whether two texts are anagrams");
            System.out.println("2 - List previously entered anagrams for a text");
            System.out.println("3 - Exit");
            System.out.print("Your choice: ");

            final String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> handleAnagramCheck(scanner, anagramService);
                case "2" -> handleAnagramLookup(scanner, anagramService);
                case "3" -> running = false;
                default -> System.out.println("Invalid choice. Please select 1, 2 or 3.");
            }
        }
        scanner.close();
    }

    private static void handleAnagramCheck(final Scanner scanner, final AnagramService anagramService) {
        System.out.print("Enter first text: ");
        final String input1 = scanner.nextLine();
        System.out.print("Enter second text: ");
        final String input2 = scanner.nextLine();
        try {
            final boolean isAnagram = anagramService.runAnagramCheck(input1, input2);
            printAnagramCheckResult(input1, input2, isAnagram);
        } catch (IllegalArgumentException ex) {
            printError(ex.getMessage());
        }
    }

    private static void handleAnagramLookup(final Scanner scanner, final AnagramService anagramService) {
        System.out.print("Enter text to search anagrams for: ");
        final String input = scanner.nextLine();
        try {
            final List<String> matches = anagramService.runAnagramLookup(input);
            printAnagramLookupResult(matches);
        } catch (IllegalArgumentException ex) {
            printError(ex.getMessage());
        }
    }

    private static void printAnagramCheckResult(final String input1, final String input2, final boolean isAnagram) {
        if (isAnagram) {
            System.out.println("\"" + input1 + "\" and \"" + input2 + "\" ARE anagrams of each other.");
        } else {
            System.out.println("\"" + input1 + "\" and \"" + input2 + "\" are NOT anagrams of each other.");
        }
    }

    private static void printError(final String message) {
        System.out.println("Error: " + message);
    }

    private static void printAnagramLookupResult(final List<String> matches) {
        System.out.println("Previously entered anagrams: " + matches);
    }

}
