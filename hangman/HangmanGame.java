import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman Game!");

        WordDatabase wordDatabase = new WordDatabase();
        boolean playAgain;
        do {
            String randomWord = wordDatabase.getRandomWord();
            GameLogic gameLogic = new GameLogic(randomWord);

            while (!gameLogic.isGameOver() && !gameLogic.isGameWon()) {
                gameLogic.displayState();

                System.out.print("\nEnter your guess (a single letter): ");
                char guess = scanner.nextLine().toLowerCase().charAt(0);

                if (gameLogic.processGuess(guess)) {
                    System.out.println("Correct Guess!");
                } else {
                    System.out.println("Wrong Guess!");
                }
            }

            if (gameLogic.isGameWon()) {
                System.out.println("\nCongratulations! You've guessed the word: " + randomWord);
            } else {
                System.out.println("\nGame Over! The word was: " + randomWord);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        } while (playAgain);

        System.out.println("Thanks for playing Hangman! Goodbye!");
        scanner.close();
    }
}
