package com.innowise.task2.parser;

import com.innowise.task2.entity.TextComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextParserTest {

  @Test
  public void testParseAndRestoreSimpleText() {
    String input = """
                Hello, world!
                This is a test.
                """;
    AbstractTextParser parserChain = buildParserChain();
    String expected = "\tHello, world!\n\tThis is a test.";

    TextComponent actualParsed = parserChain.parse(input);
    String actualRestored = actualParsed.toString();

    assertEquals(expected, actualRestored);
  }

  @Test
  public void testParseAndRestoreWithMultipleSpacesAndNewlines() {
    String input = "  Hello,   world!  \n\n  Привет   бро. ";
    AbstractTextParser parserChain = buildParserChain();
    String expected = "\tHello, world!\n\tПривет бро.";

    TextComponent actualParsed = parserChain.parse(input);
    String actualRestored = actualParsed.toString();

    assertEquals(expected, actualRestored);
  }

  private AbstractTextParser buildParserChain() {
    ParagraphParser paragraphParser = new ParagraphParser();
    SentenceParser sentenceParser = new SentenceParser();
    WordAndSymbolParser wordAndSymbolParser = new WordAndSymbolParser();
    LetterParser letterParser = new LetterParser();

    paragraphParser.setNext(sentenceParser);
    sentenceParser.setNext(wordAndSymbolParser);
    wordAndSymbolParser.setNext(letterParser);

    return paragraphParser;
  }
}
