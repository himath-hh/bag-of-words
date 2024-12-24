import java.io.File;
import java.util.Scanner;

/**
 * @author Himath Helessage
 * The Main class creates a user interface and allows the user to operate on the text file
 */
public class Main {
    /**
     * main method that runs the bag of words and geography game
     * @param args command-line arguments - I did not use them
     * @exception wordNotFound if word is not there
     */
    public static void main(String[] args) throws Exception {

        //try catch block to catch rogue user inputs
        try {
            //Scanner for user input and scraper for generating the bag
            Scanner input = new Scanner(System.in);
            Scraper scraper = new Scraper();
            
            BagOfWordsWithGame bagOfWords;

            System.out.print("Enter file path for bag of words: ");
            String fileName = input.nextLine();

            File file = new File(fileName);

            if (!file.exists()) {
                System.err.println("Error: File not found.");
                System.exit(0);
            }

            //creates a BagOfWordsWithGame object with the words
            bagOfWords = (BagOfWordsWithGame) scraper.generateBagOfWords(fileName);

            //flag to keep track when user wants to exit
            boolean running = true;

            while (running) {
                //printers for the user interface

                System.out.println("----------------------------------------------");
                System.out.println("Select an option:");
                System.out.println("1: Display the bag");
                System.out.println("2: Show total word count");
                System.out.println("3: Show word frequency");
                System.out.println("4: Show words starting with a letter");
                System.out.println("5: Show log probability");
                System.out.println("6: Start Geography Game");
                System.out.println("7: Exit");
                System.out.println("----------------------------------------------");

                //user option goes here
                System.out.print("Choice: ");
                int choice = input.nextInt();
                input.nextLine();

                //evaluates user choices and performs respective actions
                switch (choice) {

                    case 1: //prints the bag
                        System.out.print("The Bag of Words: ");
                        System.out.println(bagOfWords.toString());
                        break;

                    case 2: //prints the number of words in the bag
                        System.out.println("Total word count: " + bagOfWords.getTotalWordCount());
                        break;

                    case 3: //gets the frequency of a word given user input of word
                        System.out.print("Enter word to check frequency: ");
                        String word = input.nextLine();

                        System.out.println("Frequency of \"" + word + "\": " + bagOfWords.getFrequency(word));
                        break;

                    case 4: //gets a list of words that begin with a user provided letter
                        System.out.print("Enter starting letter: ");
                        String letter = input.nextLine().substring(0, 1).toLowerCase();

                        //checks for sorting request
                        System.out.print("Do you want the list to be sorted (1 - Yes, 0 - No): ");
                        int sortFlag = input.nextInt();
                        
                        System.out.println("Words starting with '" + letter + "': " + bagOfWords.getWordsBeginWith(letter.charAt(0), sortFlag == 1 ? true : false));
                        break;

                    case 5: //gets the log probability of a word provided by the user
                        System.out.print("Enter word to check log prabability: ");
                        String logWord = input.nextLine();

                        System.out.println("Log Probability of \"" + logWord + "\": " + bagOfWords.getLogProbability(logWord));
                    break;

                    case 6: //starts a geography game
                        System.out.print("Enter starting word for Geography Game: ");
                        String startWord = input.nextLine();

                        System.out.print("Enter maximum turns: ");
                        int maxTurns = input.nextInt();

                        //while loop to ensure no negative turns values
                        while (maxTurns <= 0) {
                            System.out.print("Enter a value greater than zero: ");
                            maxTurns = input.nextInt();
                        }

                        input.nextLine();

                        //starts the geography game
                        bagOfWords.startGame(startWord, maxTurns);
                        break;

                    case 7: //exit, with flag changes
                        System.out.println("Exit!");
                        running = false;
                        break;

                    default: // if user enters any other number
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            input.close();

        //catches user input errors
        } catch (Exception e) {
            System.err.println("Error: Rogue Value entered");
        }
    }
}
