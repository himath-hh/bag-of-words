# Bag of Words

A Java-based application that allows users to process text files, create a bag of words, and play a geography word game. The application includes features like word frequency analysis, probability calculations, and sorting words based on user-defined criteria.

---

## Features

1. **Bag of Words**
   - Create a bag of words from a text file.
   - Retrieve word frequency.
   - Get a list of words starting with a specific letter.
   - Calculate the log probability of a word.

2. **Geography Game**
   - Play a turn-based geography word game.
   - Words must begin with the last letter of the previous word.
   - Prevents reuse of words.

3. **Text File Processing**
   - Process input text files to create a bag of words.
   - Remove punctuation and normalize text.

---

## Prerequisites

- Java Development Kit (JDK) 8 or higher.
- A text file containing words for creating the bag of words.

---

## How to Run

### Using the JAR File

1. **Download the JAR File:**
   Download the precompiled JAR file named BagOfWords.jar

2. **Run the Application:**
   Open a terminal or command prompt and execute the following command:
   ```bash
   java -jar BagOfWords.jar
   ```

3. **Follow the Prompts:**
   The application will guide you through the available features.

---

## File Input Example

Provide a plain text file as input for creating the bag of words. Example content:
```
This is a sample text file.
It contains words for the bag of words.
Let's create something interesting!
```

---

## Usage Instructions

Upon running the application, you will see a menu with the following options:

1. **Display the bag**
2. **Show total word count**
3. **Show word frequency**
4. **Show words starting with a letter**
5. **Show log probability**
6. **Start Geography Game**
7. **Exit**

Simply enter the number corresponding to the desired option and follow the prompts.

---

## Sample Gameplay

1. Start the geography game by entering a word and the maximum number of turns.
2. Take turns entering words that begin with the last letter of the previous word.
3. The game ends when a player cannot provide a valid word.

---

## Code Overview

- **`BagOfWords`**: Handles core functionality like adding words, retrieving frequencies, and calculating probabilities.
- **`BagOfWordsWithGame`**: Extends `BagOfWords` and includes the geography game logic.
- **`Main`**: Provides a user interface for interacting with the application.
- **`Scraper`**: Processes text files to populate the bag of words.

---

## License

This project is licensed under the [MIT License](LICENSE.md)
