import java.util.ArrayList;
import java.util.Random;

public class WordDatabase {
    private ArrayList<String> words;

    public WordDatabase() {
        words = new ArrayList<>();
        populateWords();
    }

    private void populateWords() {
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        words.add("grape");
        words.add("orange");
    }

    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
