package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractTextParser {

    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_DELIMITER_REGEX = "\\s+";

    @Override
    public TextComponent parse(String data) {
        logger.debug("Starting lexeme parsing for input: '{}'", data);

        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = data.strip().split(LEXEME_DELIMITER_REGEX);
        logger.debug("Split into {} lexemes", lexemes.length);

        for (String lexeme : lexemes) {
            if (!lexeme.isBlank()) {
                sentenceComposite.add(getNext().parse(lexeme));
            }
        }

        logger.debug("Finished lexeme parsing");
        return sentenceComposite;
    }
}
