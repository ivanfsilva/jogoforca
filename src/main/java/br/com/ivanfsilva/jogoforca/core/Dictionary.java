package br.com.ivanfsilva.jogoforca.core;

import br.com.ivanfsilva.jogoforca.game.GameException;
import br.com.ivanfsilva.jogoforca.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Dictionary {

    private static Dictionary instance;

    public static Dictionary getInstance() {
        if(instance == null) {
            instance = new FileDictionary();
        }
        return instance;
    }

    public abstract Word nextWord();

    public abstract String getName();

}
