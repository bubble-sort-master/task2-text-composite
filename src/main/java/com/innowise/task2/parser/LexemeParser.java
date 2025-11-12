package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;

public class LexemeParser extends AbstractTextParser {

    private static final String LEXEME_DELIMITER_REGEX = "\\s+";

    @Override
    public TextComponent parse(String data) {
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = data.strip().split(LEXEME_DELIMITER_REGEX);

        for (String lexeme : lexemes) {
            if (!lexeme.isBlank()) {
                sentenceComposite.add(getNext().parse(lexeme));
            }
        }
        return sentenceComposite;
    }
}
