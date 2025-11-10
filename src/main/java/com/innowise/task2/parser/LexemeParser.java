package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;

public class LexemeParser extends AbstractTextParser {

    private static final String LEXEME_DELIMITER_REGEX = "\\s+";

    public LexemeParser(AbstractTextParser next) {
        this.next = next;
    }

    @Override
    public TextComponent parse(String data) {
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = data.trim().split(LEXEME_DELIMITER_REGEX);

        for (String lexeme : lexemes) {
            if (!lexeme.isBlank()) {
                sentenceComposite.add(next.parse(lexeme));
            }
        }
        return sentenceComposite;
    }
}
