package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;

public class SentenceParser extends AbstractTextParser {

  private static final String SENTENCE_DELIMITER_REGEX = "(?<=[.!?])\\s+";

  @Override
  public TextComponent parse(String data) {
    TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
    String[] sentences = data.strip().split(SENTENCE_DELIMITER_REGEX);

    for (String sentence : sentences) {
      if (!sentence.isBlank()) {
        paragraphComposite.add(getNext().parse(sentence.strip()));
      }
    }
    return paragraphComposite;
  }
}
