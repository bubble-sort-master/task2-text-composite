package com.innowise.task2.service.impl;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import com.innowise.task2.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

  private static final Logger logger = LogManager.getLogger();

  @Override
  public int findMaxSentencesContainingSameWord(TextComponent text) {
    logger.debug("Starting analysis to find max sentences containing the same word");

    Map<String, Set<Integer>> wordToSentenceIds = new HashMap<>();
    int sentenceId = 0;

    for (TextComponent paragraph : text.getChildren()) {
      for (TextComponent sentence : paragraph.getChildren()) {
        Set<String> wordsInSentence = sentence.getChildren().stream()
                .filter(component -> component.getType() == ComponentType.WORD)
                .map(TextComponent::toString)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        for (String word : wordsInSentence) {
          wordToSentenceIds
                  .computeIfAbsent(word, k -> new HashSet<>())
                  .add(sentenceId);
        }

        sentenceId++;
      }
    }

    int max = wordToSentenceIds.values().stream()
            .mapToInt(Set::size)
            .max()
            .orElse(0);

    logger.debug("Max number of sentences sharing the same word: {}", max);
    return max;
  }

  @Override
  public List<String> sortSentencesByWordCount(TextComponent text) {
    logger.debug("Starting sentence sorting by word count");

    List<TextComponent> sentences = new ArrayList<>();

    for (TextComponent paragraph : text.getChildren()) {
      sentences.addAll(paragraph.getChildren());
    }

    List<String> sorted = sentences.stream()
            .sorted(Comparator.comparingInt(s ->
                    (int) s.getChildren().stream()
                            .filter(c -> c.getType() == ComponentType.WORD)
                            .count()))
            .map(TextComponent::toString)
            .collect(Collectors.toList());

    logger.debug("Sorted sentences: {}", sorted);
    return sorted;
  }

  @Override
  public TextComponent swapFirstAndLastWord(TextComponent text) {
    logger.debug("Starting word swap in each sentence");

    TextComposite newText = new TextComposite(ComponentType.TEXT);

    for (TextComponent paragraph : text.getChildren()) {
      TextComposite newParagraph = new TextComposite(ComponentType.PARAGRAPH);

      for (TextComponent sentence : paragraph.getChildren()) {
        List<TextComponent> components = sentence.getChildren();
        List<Integer> wordIndices = new ArrayList<>();

        for (int i = 0; i < components.size(); i++) {
          if (components.get(i).getType() == ComponentType.WORD) {
            wordIndices.add(i);
          }
        }

        TextComposite newSentence = new TextComposite(ComponentType.SENTENCE);

        if (wordIndices.size() >= 2) {
          int first = wordIndices.get(0);
          int last = wordIndices.get(wordIndices.size() - 1);

          logger.trace("Swapping first and last word in sentence: '{}'", sentence.toString());

          for (int i = 0; i < components.size(); i++) {
            if (i == first) {
              newSentence.add(components.get(last));
            } else if (i == last) {
              newSentence.add(components.get(first));
            } else {
              newSentence.add(components.get(i));
            }
          }
        } else {
          components.forEach(newSentence::add);
        }

        newParagraph.add(newSentence);
      }

      newText.add(newParagraph);
    }

    logger.debug("Word swap completed");
    return newText;
  }
}
