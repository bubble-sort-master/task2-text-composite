package com.innowise.task2.parser;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import com.innowise.task2.entity.impl.TextLeaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class WordAndSymbolParser extends AbstractTextParser {

  private static final Logger logger = LogManager.getLogger();
  private static final Pattern WORD_PATTERN = Pattern.compile("[\\p{L}]+");
  private static final String SPLIT_REGEX = "(?<=\\p{Punct})|(?=\\p{Punct})|\\s+";

  @Override
  public TextComponent parse(String data) {
    logger.debug("Starting word/symbol parsing for sentence: '{}'", data);

    TextComponent sentenceComposite = new TextComposite(ComponentType.SENTENCE);
    String[] parts = data.split(SPLIT_REGEX);
    logger.debug("Split sentence into {} parts", parts.length);

    for (String part : parts) {
      if (part.isBlank()) continue;

      ComponentType type = WORD_PATTERN.matcher(part).matches()
              ? ComponentType.WORD
              : ComponentType.SYMBOL;

      logger.trace("Identified part '{}' as {}", part, type);
      if (type == ComponentType.WORD) {
        sentenceComposite.add(getNext().parse(part));
      } else {
        for (char ch : part.toCharArray()) {
          sentenceComposite.add(new TextLeaf(ch, ComponentType.SYMBOL));
        }
      }
    }

    logger.debug("Finished word/symbol parsing");
    return sentenceComposite;
  }
}

