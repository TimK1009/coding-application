# Readme

## User instructions
This application checks whether two words are anagrams of each other or lists previous anagrams for a given word.
Once the application is started, the user can choose between the following modes:
- 1: The user can input two strings, and it is checked, if they are an anagram. If this is the case, the words are persisted for the duration of the runtime of the application.
- 2: The user can input one string. If the string is an anagram of a previous input from feature 1, then a list of the previous anagrams is returned.
- 3: Exits the program.

The user input is validated if it only contains letters. If there are digits or special characters, an error message will be thrown.
The exception is an apostrophe, since it can a valid character in a word, e.g. "McDonald's restaurants" = "Uncle Sam's standard rot".

## Technical instructions
This project is developed in IntelliJ. The .idea files are commited, so it can be opened and executed with IntelliJ.
Otherwise, the application can be built by Gradle and the jar-file opened via the command line.