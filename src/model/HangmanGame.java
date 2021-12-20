package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanGame {
    public static final int MAX_MISTAKES = 10;
    private int mistake;
    private String keyWord;
    private List<String> spaces;
    private boolean gameStatus;
    private int j;
    public HangmanGame(String str) throws FileNotFoundException {
        j = 0;
        spaces = new ArrayList<>();
        mistake = 0;
        new WordBank(str);
        keyWord = "";
        gameStatus = true;
        generateWord();
    }

    public void generateWord() {
        Random rand = new Random();
        keyWord = WordBank.getWords().get(rand.nextInt(WordBank.getWords().size()));
        for (int i = 0; i < keyWord.length(); i++) {
            if (String.valueOf(keyWord.charAt(i)).equals(" ")) {
                spaces.add(" ");
            } else {
                spaces.add("_");
            }
        }
    }

    public boolean checkGameStatus() {
        return gameStatus;
    }
    public void addMistake() {
        mistake += 1;
        if (mistake == MAX_MISTAKES) {
            gameStatus = false;
        }
    }

    public int getMistakes() {
        return mistake;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public List<String> getSpaces() {
        return spaces;
    }

    public void changeSpaces(int i, String str) {
        spaces.set(i,str);
    }
    
    public void checkInput(String str, int i) {
        if (str.equals(String.valueOf(keyWord.charAt(i)))) {
            changeSpaces(i, String.valueOf(getKeyWord().charAt(i)));
            System.out.print(getSpaces().get(i));
            j = 1;
        } else {
            System.out.print(getSpaces().get(i));
        }
    }

    public void incorrectGuess() {
        if (j == 0) {
            System.out.println("\nIncorrect!");
            addMistake();
            System.out.println("You have " + (MAX_MISTAKES - getMistakes()) + " attempts remaining");
        } else {
            j = 0;
        }
    }
}
