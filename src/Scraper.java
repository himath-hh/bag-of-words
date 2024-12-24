import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Himath Helessage
 * the Scraper class creates a bag of words using a text file
 */
public class Scraper {
    private BagOfWordsWithGame bagOfWords;

    /**
     * Constructor for Scraper makes a new BagOfWords object
     */
    public Scraper() {
        bagOfWords = new BagOfWordsWithGame();
    }

    /*
     * method that generates a bag of words
     * @return a bag of words object
     */
    public BagOfWordsWithGame generateBagOfWords(String fileName) {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                //trims whitespaces, makes line to lowercase, replaces punctuation
                //the replace all for hyphen is used because they are not detected as punctuation during debugging
                line = line.trim().toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("—", " ");

                //splits words by spaces and puts them into an array
                String[] wordsArray = line.split("\\s+");

                //adds the words to the bag
                for (String word : wordsArray) {
                    bagOfWords.updateBag(word);
                }
            }
            
            //confirmation message
            System.out.println("A bag is created successfully!");
        
        //catches file errors
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }

        return bagOfWords;
    }
}
