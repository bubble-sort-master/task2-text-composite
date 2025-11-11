package com.innowise.task2.parser;

import com.innowise.task2.entity.TextComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String expected = "Hello, world!\nThis is a test.";

        // when
        TextComponent actualParsed = parserChain.parse(input);
        String actualRestored = actualParsed.restore();

        // then
        assertEquals(expected, actualRestored);
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
        String expected = "Hello, world!\nAnother line.";

        // when
        TextComponent actualParsed = parserChain.parse(input);
        String actualRestored = actualParsed.restore();

        // then
        assertEquals(expected, actualRestored);
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
        String expected = "Wow! Really? Yes.";

        // when
        TextComponent actualParsed = parserChain.parse(input);
        String actualRestored = actualParsed.restore();

        // then
        assertEquals(expected, actualRestored);
    }
}
