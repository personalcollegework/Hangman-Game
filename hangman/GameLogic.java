import java.util.HashSet;

public class GameLogic {
    private String wordToGuess;
    private HashSet<Character> guessedLetters;
    private int remainingAttempts;
    private StringBuilder currentProgress;

    public GameLogic(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.guessedLetters = new HashSet<>();
        this.remainingAttempts = 6;
        this.currentProgress = new StringBuilder("_".repeat(wordToGuess.length()));
    }

    public void displayState() {
        System.out.println("\nWord: " + currentProgress);
        System.out.println("Remaining Attempts: " + remainingAttempts);
        System.out.println("Guessed Letters: " + guessedLetters);
    }

    public boolean processGuess(char guess) {
        if (guessedLetters.contains(guess)) {
            System.out.println("You already guessed this letter!");
            return false;
        }

        guessedLetters.add(guess);
        if (wordToGuess.contains(String.valueOf(guess))) {
            updateCurrentProgress(guess);
            return true;
        } else {
            remainingAttempts--;
            return false;
        }
    }

    private void updateCurrentProgress(char guess) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                currentProgress.setCharAt(i, guess);
            }
        }
    }

    public boolean isGameWon() {
        return currentProgress.toString().equals(wordToGuess);
    }

    public boolean isGameOver() {
        return remainingAttempts == 0;
    }
}
