package br.com.ivanfsilva.jogoforca.game;

import br.com.ivanfsilva.jogoforca.core.Config;
import br.com.ivanfsilva.jogoforca.core.Dictionary;
import br.com.ivanfsilva.jogoforca.core.InvalidCharacterException;
import br.com.ivanfsilva.jogoforca.core.Word;
import br.com.ivanfsilva.jogoforca.ui.UI;

import java.util.HashSet;
import java.util.Set;

public class Game {

    public void start() {
        UI.print("Bem vindo ao jogo da Forca!");

        Dictionary dictionary = Dictionary.getInstance();
        Word word = dictionary.nextWord();

        UI.print("A palavra tem " + word.size() + " letras");

        Set<Character> usedChars = new HashSet<>();
        int errorCount = 0;
        int maxErrors = Integer.parseInt(Config.get("maxErrors"));

        UI.print("Você pode errar no máximo " + maxErrors + " vez(es)");

        while (true) {
            UI.print(word);
            UI.printNewLine();

            char c;

            try {
                c = UI.readChar("Digite uma letra");
                if (usedChars.contains(c)) {
                    throw new InvalidCharacterException("Esta letra já foi utilizada");
                }

                usedChars.add(c);

                if (word.hasChar(c)) {
                    UI.print("Você acertou uma letra!");
                } else {
                    errorCount++;

                    if (errorCount < maxErrors) {
                        UI.print("Você errou! Ainda pode errar " +
                                (maxErrors - errorCount) + " vez(es)");
                    }
                }
                UI.printNewLine();
                if(word.discovered()) {
                    UI.print("PARABÉNS! Você acertou  palavra correta: " + word.getOriginalWord());
                    UI.print("Fim do Jogo!");
                    break;
                }

                if (errorCount == maxErrors) {
                    UI.print("PARABÉNS! Você perdeu o jogo! A palavra correta era: " + word.getOriginalWord());
                    UI.print("Fim do Jogo!");
                    break;
                }

            } catch (InvalidCharacterException e) {
                UI.print("Erro: " + e.getMessage());
                UI.printNewLine();
            }
        }
    }
}
