package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends AbstractTextParser {

  private static final Logger logger = LogManager.getLogger();
  private static final String SENTENCE_DELIMITER_REGEX = "(?<=[.!?])\\s+";

  @Override
  public TextComponent parse(String data) {
    logger.debug("Starting sentence parsing");

    TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
    String[] sentences = data.strip().split(SENTENCE_DELIMITER_REGEX);
    logger.debug("Split input into {} sentences", sentences.length);

    for (String sentence : sentences) {
      if (!sentence.isBlank()) {
        paragraphComposite.add(getNext().parse(sentence.strip()));
      }
    }

    logger.debug("Finished sentence parsing");
    return paragraphComposite;
  }
}
