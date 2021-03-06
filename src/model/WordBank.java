package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WordBank {
    private static List<String> words;
    public WordBank(String str) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(str));
        words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
    }

    public static List<String> getWords() {
        return Collections.unmodifiableList(words);
    }
}
