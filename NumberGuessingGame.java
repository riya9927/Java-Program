import java.util.*;

class NumberGuessingGame {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private final Map<Integer, DifficultyLevel> difficultyLevels;
    private final Map<String, Integer> highScores;
    private final Scanner scanner;

    public NumberGuessingGame() {
        this.difficultyLevels = new HashMap<>();
        this.difficultyLevels.put(1, new DifficultyLevel("Easy", 10));
        this.difficultyLevels.put(2, new DifficultyLevel("Medium", 5));
        this.difficultyLevels.put(3, new DifficultyLevel("Hard", 3));

        this.highScores = new HashMap<>();
        for (DifficultyLevel level : difficultyLevels.values()) {
            highScores.put(level.name, Integer.MAX_VALUE);
        }

        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        while (true) {
            displayWelcomeMessage();
            playRound();
            displayHighScores();

            System.out.print("\nWould you like to play again? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("\nThanks for playing! Goodbye!");
                break;
            }
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("\nWelcome to the Number Guessing Game!");
        System.out.printf("I'm thinking of a number between %d and %d.%n", MIN_NUMBER, MAX_NUMBER);
    }

    private DifficultyLevel getDifficultyLevel() {
        System.out.println("\nPlease select the difficulty level:");
        for (Map.Entry<Integer, DifficultyLevel> entry : difficultyLevels.entrySet()) {
            System.out.printf("%d. %s (%d chances)%n", 
                entry.getKey(), 
                entry.getValue().name, 
                entry.getValue().chances);
        }

        while (true) {
            System.out.print("\nEnter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (difficultyLevels.containsKey(choice)) {
                    DifficultyLevel level = difficultyLevels.get(choice);
                    System.out.printf("\nGreat! You have selected the %s difficulty level.%n", level.name);
                    return level;
                }
                System.out.println("Invalid choice. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void provideHint(int targetNumber, int guess, int attempts, int maxAttempts) {
        if (attempts == maxAttempts / 2) {
            if (targetNumber % 2 == 0) {
                System.out.println("Hint: The number is even!");
            } else {
                System.out.println("Hint: The number is odd!");
            }
        } else if (attempts == maxAttempts - 1) {
            int rangeSize = Math.abs(targetNumber - guess);
            System.out.printf("Hint: The number is within %d of your last guess!%n", rangeSize);
        }
    }

    private void playRound() {
        Random random = new Random();
        int targetNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        DifficultyLevel level = getDifficultyLevel();
        int attempts = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("Let's start the game!");

        while (attempts < level.chances) {
            System.out.print("\nEnter your guess: ");
            int guess;

            try {
                guess = Integer.parseInt(scanner.nextLine());
                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    System.out.printf("Please enter a number between %d and %d.%n", MIN_NUMBER, MAX_NUMBER);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            attempts++;

            if (guess == targetNumber) {
                double elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0;
                System.out.printf("\nCongratulations! You guessed the correct number in %d attempts!%n", attempts);
                System.out.printf("Time taken: %.2f seconds%n", elapsedTime);

                // Update high score
                if (attempts < highScores.get(level.name)) {
                    highScores.put(level.name, attempts);
                    System.out.printf("New high score for %s difficulty!%n", level.name);
                }
                return;
            }

            System.out.print("Incorrect! ");
            if (guess < targetNumber) {
                System.out.println("The number is greater than your guess.");
            } else {
                System.out.println("The number is less than your guess.");
            }

            System.out.printf("Attempts remaining: %d%n", level.chances - attempts);
            provideHint(targetNumber, guess, attempts, level.chances);
        }

        System.out.printf("\nGame Over! The number was %d.%n", targetNumber);
    }

    private void displayHighScores() {
        System.out.println("\nHigh Scores:");
        for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
            if (entry.getValue() == Integer.MAX_VALUE) {
                System.out.printf("%s: No score yet%n", entry.getKey());
            } else {
                System.out.printf("%s: %d attempts%n", entry.getKey(), entry.getValue());
            }
        }
    }

    private static class DifficultyLevel {
        final String name;
        final int chances;

        DifficultyLevel(String name, int chances) {
            this.name = name;
            this.chances = chances;
        }
    }

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.playGame();
    }
}
