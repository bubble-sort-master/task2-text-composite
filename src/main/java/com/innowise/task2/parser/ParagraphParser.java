package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;

public class ParagraphParser extends AbstractTextParser {

    private static final String PARAGRAPH_DELIMITER_REGEX = "\\n+";

    public ParagraphParser(AbstractTextParser next) {
        this.next = next;
    }

    @Override
    public TextComponent parse(String data) {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = data.trim().split(PARAGRAPH_DELIMITER_REGEX);

        for (String paragraph : paragraphs) {
            if (!paragraph.isBlank()) {
                textComposite.add(next.parse(paragraph.trim()));
            }
        }
        return textComposite;
    }
}
