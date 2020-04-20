package br.com.ivanfsilva.jogoforca.core;

import br.com.ivanfsilva.jogoforca.game.GameException;
import br.com.ivanfsilva.jogoforca.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {

    private static final String FILE_NAME = "dicionario.txt";

    private static Dictionary instance;

    private List<String> words = new ArrayList<>();

    public Dictionary() {
        load();
    }

    public static Dictionary getInstance() {
        if(instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    private void load() {
        try (Scanner scanner =
                     new Scanner(getClass()
                             .getResourceAsStream("/" + FILE_NAME))) {

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                words.add(word);
            }

            if (words.size() == 0) {
                throw new GameException("A lista de palavras não pode estar vazia");
            }

        }

    }

    public Word nextWord() {
        int pos = RandomUtils.newRandomNumber(0, words.size());
        return new Word(words.get(pos));
    }

}
