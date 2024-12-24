import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Himath Helessage
 * the BagOfWordsWithGame method extends the BagOfWords method to create a geography game
 */
public class BagOfWordsWithGame extends BagOfWords {
    private List<String> usedWords = new ArrayList<>();

    //hashmap used for memoization
    private Map<Character, List<String>> memoizedWordList = new HashMap<>();

    /**
     * Constructor with super call
     */
    public BagOfWordsWithGame() {
        super();
    }

    /**
     * this method starts a geography game
     * @param startWord word to start with
     * @param maxTurns maximum number of turns
     */
    public void startGame(String startWord, int maxTurns) {
        System.out.println("... Game begins ...");

        //plays the geography game
        playTurn(startWord, maxTurns, 1);

        System.out.println("... Game ends ...");
    }

    /**
     * plays a turn of a player and is called recursively
     * @param currentWord word that a player played
     * @param remainingTurns how many turns remain
     * @param playerNumber 1 or 2
     */
    private void playTurn(String currentWord, int remainingTurns, int playerNumber) {

        //maximum turns is reached
        if (remainingTurns == 0) {
            System.out.println("Ties!");
            return;
        }

        System.out.println("Player " + playerNumber + ": " + currentWord);

        //adds the played word into the usedWords list
        usedWords.add(currentWord);

        //gets the end letter of the last played word
        char nextChar = currentWord.charAt(currentWord.length() - 1);

        //list of possibly playable words memoized
        List<String> possibleWords = getWordsBeginWithMemoized(nextChar);

        //if no words left in the possibleWords list, other player loses 
        if (possibleWords.isEmpty()) {
            System.out.println("Player " + (3 - playerNumber) + " loses!");
            return;
        }

        //gets the next word randomly from the memoized list
        String nextWord = possibleWords.get((int) (Math.random() * possibleWords.size()));

        //removes the played word
        removedUsedWord(nextChar, nextWord);

        //recursive call to next turn with 1 less remaining turn and switched player
        playTurn(nextWord, remainingTurns - 1, 3 - playerNumber);
    }

    /**
     * gets memoized list of words
     * @param c first letter of a word
     * @return memoized list of words starting with character c
     */
    private List<String> getWordsBeginWithMemoized(char c) {
        //returns the list if character is found
        if (memoizedWordList.containsKey(c)) {
            return memoizedWordList.get(c);
        }
        
        //list of words beginning with character c
        List<String> wordsBeginsWith = getWordsBeginWith(c);

        //puts the list into the memoized map
        memoizedWordList.put(c, wordsBeginsWith);

        return wordsBeginsWith;
    }

    /**
     * this method removes the used words from memoized list
     * @param c character key reference to list
     * @param word word to be removed from list
     */
    private void removedUsedWord(char c, String word) {

        //removes word from the list beginning with letter c
        if (memoizedWordList.containsKey(c)) {

            List<String> wordsByChar = memoizedWordList.get(c);
            wordsByChar.remove(word);
            
            //if list is empty the key is removed from the map
            if (wordsByChar.isEmpty()) {
                memoizedWordList.remove(c);
            }
        }
    }
}
