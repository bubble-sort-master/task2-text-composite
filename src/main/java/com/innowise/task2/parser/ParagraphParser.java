package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractTextParser {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_DELIMITER_REGEX = "\\n+";

    @Override
    public TextComponent parse(String data) {
        logger.debug("Starting paragraph parsing");

        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = data.strip().split(PARAGRAPH_DELIMITER_REGEX);
        logger.debug("Split input into {} paragraphs", paragraphs.length);

        for (String paragraph : paragraphs) {
            if (!paragraph.isBlank()) {
                textComposite.add(getNext().parse(paragraph.strip()));
            }
        }

        logger.debug("Finished paragraph parsing");
        return textComposite;
    }
}
