import model.HangmanGame;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class HangmanGameApp {
    private Scanner input;
    private HangmanGame hangmanGame;

    public HangmanGameApp() throws FileNotFoundException {
        input = new Scanner(System.in);

        startUp();
    }


    public void startUp() throws FileNotFoundException {
        System.out.println("Welcome to Hangman!");
        System.out.println("Which mode would you like to play?");
        System.out.println("a) regular \nb) League of Legends Champion Names");
        String str = input.next();
        if (str.equals("a")) {
            hangmanGame = new HangmanGame("data/words_alpha.txt");
            runGame();
        } else if (str.equals("b")) {
            hangmanGame = new HangmanGame("data/LeagueChamps.txt");
            runGame();
        } else {
            System.out.println("Invalid Input, Please Try again");
            startUp();
        }
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
        if (command.equals(hangmanGame.getKeyWord().toLowerCase())) {
            System.out.println("Congratulations you win!");
            System.exit(0);
        }

        for (int i = 0; i < hangmanGame.getKeyWord().length(); i++) {
            hangmanGame.checkInput(command, i);
        }
        hangmanGame.incorrectGuess();
    }

    private void displayMenu() {
        int size = hangmanGame.getKeyWord().length();
        System.out.println("Your word has " + size + " letters");
        for (int i = 0; i < hangmanGame.getKeyWord().length(); i++) {
            if (String.valueOf(hangmanGame.getKeyWord().charAt(i)).equals(" ")) {
                System.out.print(" ");
            } else {
                System.out.print("_");
            }
        }
    }
}
