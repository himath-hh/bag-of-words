import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Himath Helessage
 * The BagOfWWords class creates a bag of words using a hashmap with relevant supporting methods
 */
public class BagOfWords {
    //variables for the bag and to count the number of words
    private HashMap<String, Integer> bagOfWords;
    private int wordCounter;

    /**
     * Constructor for the BagOfWords creates a new empty hashmap and initializes the word counter to zero
     */
    public BagOfWords() {
        bagOfWords = new HashMap<>();
        wordCounter = 0;
    }

    /**
     * gets the total word count
     * @return total word count
     */
    public int getTotalWordCount() {
        return wordCounter;
    }

    /**
     * gets the number of occurences of a given string
     * @param w string that is being checked for
     * @return number of occurences
     */
    public int getFrequency(String w) {
        return bagOfWords.getOrDefault(w, 0);
    }
    
    /**
     * overrides the toString method to print the bagOfWords using the hashmap toString method
     */
    @Override
    public String toString() {
        return bagOfWords.toString(); 
    }

    /**
     * updates the bag with a given word
     * @param w word to be added to the bag
     */
    public void updateBag(String w) {
        //if the bag already contains the word, it increases the frequency without adding the same word again
        if (bagOfWords.containsKey(w)) {
            bagOfWords.put(w, bagOfWords.get(w) + 1);

            //number of words in the bag incremented
            wordCounter++;
        
        //if not, makes sure the word is not a blank/ null character and adds it to the bag
        } else if (w != "") {
            bagOfWords.put(w, 1);

            //number of words in the bag incremented
            wordCounter++;
        }
    }

    /**
     * method that returns words beginning with a given character
     * @param c character that needs to be checked
     * @return a List of words that begin with the character
     */
    public List<String> getWordsBeginWith(char c) {
        List<String> wordList = new ArrayList<>();

        //iterates through the bag and checks whether the first letter of a key corresponds to the character
        for (String thisWord : bagOfWords.keySet()) {

            if (thisWord.charAt(0) == c) {
                //adds the matching word to the list
                wordList.add(thisWord);
            }
        }

        return wordList;
    }

    /**
     * method that returns words beginning with a given character ordered using insertion sort
     * @param c character that needs to be checked
     * @param sort whether the list needs to be sorted
     * @return a List of words that begin with the character
     */
    public List<String> getWordsBeginWith(char c, boolean sort) {
        List<String> wordList = getWordsBeginWith(c);

        //insertion sort algorithm
        if (sort == true) {
            for (int i = 1; i < wordList.size(); i++) {
                String key = wordList.get(i);
                int j = i - 1;
                
                while (j >= 0 && wordList.get(j).compareTo(key) > 0) {
                    wordList.set(j + 1, wordList.get(j));
                    j = j - 1;
                }

                wordList.set(j + 1, key);
            }

            return wordList;
    
        } else {
            return wordList;
        }
    }

    /**
     * method that gets the log probability of a given word
     * @param w word that needs to be checked
     * @return log probability
     * @throws Exception when the word cannot be found
     */
    public float getLogProbability(String w) throws Exception{
        if (bagOfWords.containsKey(w) == false) {
            throw new Exception("Word not found");
        }

        //formula for calculating log probability
        return (float) (Math.log(getFrequency(w)) - Math.log(wordCounter));
    }
}
