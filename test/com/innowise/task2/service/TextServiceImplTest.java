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
    // given
    TextComposite text = new TextComposite(ComponentType.TEXT);
    TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);

    TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
    sentence1.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("Hello", ComponentType.WORD));
    }});
    sentence1.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("world", ComponentType.WORD));
    }});

    TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
    sentence2.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("world", ComponentType.WORD));
    }});
    sentence2.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("privet", ComponentType.WORD));
    }});

    TextComposite sentence3 = new TextComposite(ComponentType.SENTENCE);
    sentence3.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("unique", ComponentType.WORD));
    }});

    paragraph.add(sentence1);
    paragraph.add(sentence2);
    paragraph.add(sentence3);
    text.add(paragraph);

    // when
    int actual = service.findMaxSentencesContainingSameWord(text);

    // then
    int expected = 2;
    assertEquals(expected, actual);
  }

  @Test
  public void testSortSentencesByLexemeCount() {
    // given
    TextComposite text = new TextComposite(ComponentType.TEXT);
    TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);

    TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
    sentence1.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("One", ComponentType.WORD));
    }});

    TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
    sentence2.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("Two", ComponentType.WORD));
    }});
    sentence2.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("words", ComponentType.WORD));
    }});

    TextComposite sentence3 = new TextComposite(ComponentType.SENTENCE);
    sentence3.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("Three", ComponentType.WORD));
    }});
    sentence3.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("lexemes", ComponentType.WORD));
    }});
    sentence3.add(new TextComposite(ComponentType.LEXEME) {{
      add(new TextLeaf("here", ComponentType.WORD));
    }});

    paragraph.add(sentence3);
    paragraph.add(sentence1);
    paragraph.add(sentence2);
    text.add(paragraph);

    // when
    List<String> actual = service.sortSentencesByLexemeCount(text);

    // then
    List<String> expected = List.of("One", "Two words", "Three lexemes here");
    assertEquals(expected, actual);
  }

  @Test
  public void testSwapFirstAndLastLexeme() {
    // given
    TextComposite text = new TextComposite(ComponentType.TEXT);
    TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
    TextComposite sentence = new TextComposite(ComponentType.SENTENCE);

    TextComposite lexeme1 = new TextComposite(ComponentType.LEXEME);
    lexeme1.add(new TextLeaf("First;", ComponentType.WORD));

    TextComposite lexeme2 = new TextComposite(ComponentType.LEXEME);
    lexeme2.add(new TextLeaf("middle", ComponentType.WORD));

    TextComposite lexeme3 = new TextComposite(ComponentType.LEXEME);
    lexeme3.add(new TextLeaf("Last", ComponentType.WORD));

    sentence.add(lexeme1);
    sentence.add(lexeme2);
    sentence.add(lexeme3);

    paragraph.add(sentence);
    text.add(paragraph);

    // when
    TextComponent result = service.swapFirstAndLastLexeme(text);

    // then
    List<TextComponent> lexemes = result.getChildren().get(0).getChildren().get(0).getChildren();

    String expectedFirst = "Last";
    String expectedSecond = "middle";
    String expectedThird = "First;";

    String actualFirst = lexemes.get(0).restore();
    String actualSecond = lexemes.get(1).restore();
    String actualThird = lexemes.get(2).restore();

    assertAll(
            () -> assertEquals(expectedFirst, actualFirst),
            () -> assertEquals(expectedSecond, actualSecond),
            () -> assertEquals(expectedThird, actualThird)
    );
  }
}
