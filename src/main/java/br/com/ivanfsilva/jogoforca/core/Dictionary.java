package br.com.ivanfsilva.jogoforca.core;

import br.com.ivanfsilva.jogoforca.game.GameException;
import br.com.ivanfsilva.jogoforca.utils.RandomUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Dictionary {

    private static Dictionary instance;

    public static Dictionary getInstance() {
        if(instance == null) {
            try {
                String dictionaryClassName = Config.get("dictionaryClassName");
                Class<?> clazz = Class.forName(dictionaryClassName);
                Constructor<?> constructor = clazz.getConstructor();
                instance = (Dictionary) constructor.newInstance();
            } catch (Exception e) {
                throw  new RuntimeException(e);
            }
        }
        return instance;
    }

    public abstract Word nextWord();

    public abstract String getName();

}
