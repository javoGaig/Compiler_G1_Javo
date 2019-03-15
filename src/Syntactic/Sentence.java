package Syntactic;

import Lexicon.Token;

import java.util.List;

public class Sentence {
    private List<Token> tokens;

    public Sentence(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tokens.size(); i++) {
            stringBuilder.append(tokens.get(i).getName()).append(" ");
        }

        return stringBuilder.toString();
    }

}
