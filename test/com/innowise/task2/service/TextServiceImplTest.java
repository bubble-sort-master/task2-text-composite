package com.innowise.task2.service;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import com.innowise.task2.entity.impl.TextLeaf;
import com.innowise.task2.service.impl.TextServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextServiceImplTest {

  private final TextService service = new TextServiceImpl();

  @Test
  public void testFindMaxSentencesContainingSameWord() {
    TextComponent sentence1 = createSentence("Hello", "world");
    TextComponent sentence2 = createSentence("world", "privet");
    TextComponent sentence3 = createSentence("unique");

    TextComponent paragraph = createComposite(ComponentType.PARAGRAPH, sentence1, sentence2, sentence3);
    TextComponent text = createComposite(ComponentType.TEXT, paragraph);

    int expected = 2;
    int actual = service.findMaxSentencesContainingSameWord(text);

    assertEquals(expected, actual);
  }

  @Test
  public void testSortSentencesByWordCount() {
    TextComponent sentence1 = createSentence("Three", "lexemes", "here");
    TextComponent sentence2 = createSentence("Two", "words");
    TextComponent sentence3 = createSentence("One");

    TextComponent paragraph = createComposite(ComponentType.PARAGRAPH, sentence3, sentence1, sentence2);
    TextComponent text = createComposite(ComponentType.TEXT, paragraph);

    List<String> expected = List.of("One", "Two words", "Three lexemes here");
    List<String> actual = service.sortSentencesByWordCount(text);

    assertEquals(expected, actual);
  }

  @Test
  public void testSwapFirstAndLastWord() {
    TextComponent sentence = createSentence("First", "middle", "Last");
    TextComponent paragraph = createComposite(ComponentType.PARAGRAPH, sentence);
    TextComponent text = createComposite(ComponentType.TEXT, paragraph);

    TextComponent result = service.swapFirstAndLastWord(text);
    List<TextComponent> words = result.getChildren().getFirst().getChildren().getFirst().getChildren();

    String expectedFirst = "Last";
    String expectedSecond = "middle";
    String expectedThird = "First";

    String actualFirst = words.get(0).toString();
    String actualSecond = words.get(1).toString();
    String actualThird = words.get(2).toString();

    assertAll(
            () -> assertEquals(expectedFirst, actualFirst),
            () -> assertEquals(expectedSecond, actualSecond),
            () -> assertEquals(expectedThird, actualThird)
    );
  }

  private TextComponent createWord(String word) {
    TextComposite wordComposite = new TextComposite(ComponentType.WORD);
    for (char ch : word.toCharArray()) {
      wordComposite.add(new TextLeaf(ch, ComponentType.LETTER));
    }
    return wordComposite;
  }

  private TextComponent createSentence(String... words) {
    TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
    for (String word : words) {
      sentence.add(createWord(word));
    }
    return sentence;
  }

  private TextComponent createComposite(ComponentType type, TextComponent... children) {
    TextComposite composite = new TextComposite(type);
    for (TextComponent child : children) {
      composite.add(child);
    }
    return composite;
  }
}
