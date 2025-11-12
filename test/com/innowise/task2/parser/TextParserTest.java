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
        AbstractTextParser parserChain = buildParserChain();
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
        AbstractTextParser parserChain = buildParserChain();
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
        AbstractTextParser parserChain = buildParserChain();
        String expected = "Wow! Really? Yes.";

        // when
        TextComponent actualParsed = parserChain.parse(input);
        String actualRestored = actualParsed.restore();

        // then
        assertEquals(expected, actualRestored);
    }

    private AbstractTextParser buildParserChain() {
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordAndSymbolParser wordAndSymbolParser = new WordAndSymbolParser();

        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordAndSymbolParser);

        return paragraphParser;
    }
}
