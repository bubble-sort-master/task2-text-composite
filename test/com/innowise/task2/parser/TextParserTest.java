package com.innowise.task2.parser;

import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.parser.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextParserTest {

    @Test
    public void testParseAndRestoreSimpleText() {
        // given
        String input = """
                Hello, world!
                This is a test.
                """;

        AbstractTextParser parserChain = new ParagraphParser(
                new SentenceParser(
                        new LexemeParser(
                                new WordAndSymbolParser()
                        )
                )
        );

        // when
        TextComponent parsed = parserChain.parse(input);
        String restored = parsed.restore();

        // then
        assertEquals("Hello, world!\nThis is a test.", restored);
    }

    @Test
    public void testParseAndRestoreWithMultipleSpacesAndNewlines() {
        // given
        String input = "  Hello,   world!  \n\n  Another   line. ";

        AbstractTextParser parserChain = new ParagraphParser(
                new SentenceParser(
                        new LexemeParser(
                                new WordAndSymbolParser()
                        )
                )
        );

        // when
        TextComponent parsed = parserChain.parse(input);
        String restored = parsed.restore();

        // then
        assertEquals("Hello, world!\nAnother line.", restored);
    }

    @Test
    public void testRestorePreservesPunctuation() {
        // given
        String input = "Wow! Really? Yes.";

        AbstractTextParser parserChain = new ParagraphParser(
                new SentenceParser(
                        new LexemeParser(
                                new WordAndSymbolParser()
                        )
                )
        );

        // when
        TextComponent parsed = parserChain.parse(input);
        String restored = parsed.restore();

        // then
        assertEquals("Wow! Really? Yes.", restored);
    }
}