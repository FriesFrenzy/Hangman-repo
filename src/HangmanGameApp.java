import model.HangmanGame;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class HangmanGameApp {
    private static final int MAX_MISTAKES = 10;
    private Scanner input;
    private HangmanGame hangmanGame;

    public HangmanGameApp() throws FileNotFoundException {
        hangmanGame = new HangmanGame();
        input = new Scanner(System.in);

        runGame();
    }

    public void runGame() {
        displayMenu();
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            System.out.println("\nWhat is your guess?");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else if (!hangmanGame.checkGameStatus()) {
                System.out.println("You Lost! The word was " + hangmanGame.getKeyWord());
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    private void processCommand(String command) {
        if (command.equals(hangmanGame.getKeyWord())) {
            System.out.println("Congratulations you win!");
            System.exit(0);
        }

        int j = 0;
        for (int i = 0; i < hangmanGame.getKeyWord().length(); i++) {
            String str = String.valueOf(hangmanGame.getKeyWord().charAt(i));
            if (str.equals(command)) {
                hangmanGame.changeSpaces(i, command);
                System.out.print(hangmanGame.getSpaces().get(i));
                j = 1;
            } else {
                System.out.print(hangmanGame.getSpaces().get(i));
            }
        }
        if (j == 0) {
            System.out.println("\nIncorrect!");
            hangmanGame.addMistake();
            System.out.println("You have " + (MAX_MISTAKES - hangmanGame.getMistakes()) + " attempts remaining");
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to Hangman!");
        int size = hangmanGame.getKeyWord().length();
        System.out.println("Your word has " + size + " letters");
        for (int i = 0; i < size; i++) {
            System.out.print("_");
        }
    }
}
