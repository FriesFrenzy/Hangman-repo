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
    public HangmanGame() throws FileNotFoundException {
        spaces = new ArrayList<>();
        mistake = 0;
        new WordBank();
        keyWord = "";
        gameStatus = true;
        generateWord();
    }

    public void generateWord() {
        Random rand = new Random();
        keyWord = WordBank.getWords().get(rand.nextInt(WordBank.getWords().size()));
        for (int i = 0; i < keyWord.length(); i++) {
            spaces.add("_");
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
}
