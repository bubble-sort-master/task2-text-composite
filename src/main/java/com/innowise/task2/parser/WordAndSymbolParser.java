package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import com.innowise.task2.entity.impl.TextLeaf;

import java.util.regex.Pattern;

public class WordAndSymbolParser extends AbstractTextParser {

    private static final Pattern WORD_PATTERN = Pattern.compile("[\\w]+");
    private static final String SPLIT_REGEX = "(?<=\\W)|(?=\\W)";

    public WordAndSymbolParser() {
        this.next = null;
    }

    @Override
    public TextComponent parse(String data) {
        TextComponent lexemeComposite = new TextComposite(ComponentType.LEXEME);

        for (String part : data.split(SPLIT_REGEX)) {
            if (part.isBlank()) continue;

            ComponentType type = WORD_PATTERN.matcher(part).matches()
                    ? ComponentType.WORD
                    : ComponentType.SYMBOL;

            lexemeComposite.add(new TextLeaf(part, type));
        }

        return lexemeComposite;
    }
}
