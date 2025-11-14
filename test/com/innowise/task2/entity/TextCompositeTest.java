package com.innowise.task2.entity;

import com.innowise.task2.entity.impl.TextComposite;
import com.innowise.task2.entity.impl.TextLeaf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextCompositeTest {

  @Test
  public void testToStringWithSingleWord() {
    TextComposite word = new TextComposite(ComponentType.WORD);
    word.add(new TextLeaf('O', ComponentType.LETTER));
    word.add(new TextLeaf('k', ComponentType.LETTER));

    assertEquals("Ok", word.toString());
  }

  @Test
  public void testToStringWithNestedStructure() {
    TextComposite word = new TextComposite(ComponentType.WORD);
    word.add(new TextLeaf('О', ComponentType.LETTER));
    word.add(new TextLeaf('к', ComponentType.LETTER));

    TextLeaf exclamation = new TextLeaf('!', ComponentType.SYMBOL);

    TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
    sentence.add(word);
    sentence.add(exclamation);

    TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
    paragraph.add(sentence);

    TextComposite text = new TextComposite(ComponentType.TEXT);
    text.add(paragraph);

    assertEquals("\tОк!", text.toString());
  }

}
