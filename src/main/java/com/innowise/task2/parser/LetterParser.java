package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import com.innowise.task2.entity.impl.TextLeaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LetterParser extends AbstractTextParser {

  private static final Logger logger = LogManager.getLogger();

  @Override
  public TextComponent parse(String word) {
    logger.debug("Starting letter parsing for word: '{}'", word);

    TextComposite wordComposite = new TextComposite(ComponentType.WORD);

    for (char ch : word.toCharArray()) {
      wordComposite.add(new TextLeaf(String.valueOf(ch), ComponentType.LETTER));
    }

    logger.debug("Finished letter parsing");
    return wordComposite;
  }
}
