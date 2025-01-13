import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGUI {
    private JFrame frame;
    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JLabel guessedLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton restartButton;

    private GameLogic gameLogic;
    private WordDatabase wordDatabase;

    public HangmanGUI() {
        wordDatabase = new WordDatabase();
        setupGame();
        createUI();
    }

    private void setupGame() {
        String randomWord = wordDatabase.getRandomWord();
        gameLogic = new GameLogic(randomWord);
    }

    private void createUI() {
        // Initialize frame
        frame = new JFrame("Hangman Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1));

        // Word display label
        wordLabel = new JLabel("Word: " + gameLogic.currentProgress.toString(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(wordLabel);

        // Attempts label
        attemptsLabel = new JLabel("Remaining Attempts: " + gameLogic.remainingAttempts, SwingConstants.CENTER);
        frame.add(attemptsLabel);

        // Guessed letters label
        guessedLabel = new JLabel("Guessed Letters: " + gameLogic.guessedLetters, SwingConstants.CENTER);
        frame.add(guessedLabel);

        // Guess input field and button
        JPanel inputPanel = new JPanel();
        guessField = new JTextField(5);
        guessButton = new JButton("Submit Guess");
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        frame.add(inputPanel);

        // Restart button
        restartButton = new JButton("Restart Game");
        restartButton.setVisible(false);
        frame.add(restartButton);

        // Add action listeners
        addEventListeners();

        // Show the frame
        frame.setVisible(true);
    }

    private void addEventListeners() {
        // Handle guess submissions
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = guessField.getText().toLowerCase();
                if (input.isEmpty() || input.length() != 1) {
                    JOptionPane.showMessageDialog(frame, "Please enter a single letter.");
                    return;
                }

                char guess = input.charAt(0);
                boolean correct = gameLogic.processGuess(guess);

                if (correct) {
                    JOptionPane.showMessageDialog(frame, "Correct Guess!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong Guess!");
                }

                updateUI();

                if (gameLogic.isGameWon()) {
                    JOptionPane.showMessageDialog(frame,
                            "Congratulations! You've guessed the word: " + gameLogic.wordToGuess);
                    endGame();
                } else if (gameLogic.isGameOver()) {
                    JOptionPane.showMessageDialog(frame, "Game Over! The word was: " + gameLogic.wordToGuess);
                    endGame();
                }

                guessField.setText("");
            }
        });

        // Handle game restart
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupGame();
                restartButton.setVisible(false);
                guessButton.setEnabled(true);
                guessField.setEnabled(true);
                updateUI();
            }
        });
    }

    private void updateUI() {
        wordLabel.setText("Word: " + gameLogic.currentProgress.toString());
        attemptsLabel.setText("Remaining Attempts: " + gameLogic.remainingAttempts);
        guessedLabel.setText("Guessed Letters: " + gameLogic.guessedLetters);
    }

    private void endGame() {
        guessButton.setEnabled(false);
        guessField.setEnabled(false);
        restartButton.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanGUI());
    }
}
