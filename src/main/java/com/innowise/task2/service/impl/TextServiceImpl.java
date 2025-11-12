package com.innowise.task2.service.impl;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import com.innowise.task2.entity.impl.TextComposite;
import com.innowise.task2.service.TextService;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

  @Override
  public int findMaxSentencesContainingSameWord(TextComponent text) {
    Map<String, Set<Integer>> wordToSentenceIds = new HashMap<>();
    int sentenceId = 0;

    for (TextComponent paragraph : text.getChildren()) {
      for (TextComponent sentence : paragraph.getChildren()) {
        Set<String> wordsInSentence = sentence.getChildren().stream()
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(component -> component.getType() == ComponentType.WORD)
                .map(component -> component.restore().toLowerCase())
                .collect(Collectors.toSet());

        for (String word : wordsInSentence) {
          wordToSentenceIds
                  .computeIfAbsent(word, k -> new HashSet<>())
                  .add(sentenceId);
        }

        sentenceId++;
      }
    }

    return wordToSentenceIds.values().stream()
            .mapToInt(Set::size)
            .max()
            .orElse(0);
  }


  @Override
  public List<String> sortSentencesByLexemeCount(TextComponent text) {
    List<TextComponent> sentences = new ArrayList<>();

    for (TextComponent paragraph : text.getChildren()) {
      sentences.addAll(paragraph.getChildren());
    }

    return sentences.stream()
            .sorted(Comparator.comparingInt(s -> s.getChildren().size()))
            .map(TextComponent::restore)
            .collect(Collectors.toList());
  }

  @Override
  public TextComponent swapFirstAndLastLexeme(TextComponent text) {
    TextComposite newText = new TextComposite(ComponentType.TEXT);

    for (TextComponent paragraph : text.getChildren()) {
      TextComposite newParagraph = new TextComposite(ComponentType.PARAGRAPH);

      for (TextComponent sentence : paragraph.getChildren()) {
        List<TextComponent> lexemes = sentence.getChildren();
        TextComposite newSentence = new TextComposite(ComponentType.SENTENCE);

        if (lexemes.size() >= 2) {
          newSentence.add(lexemes.get(lexemes.size() - 1));
          for (int i = 1; i < lexemes.size() - 1; i++) {
            newSentence.add(lexemes.get(i));
          }
          newSentence.add(lexemes.get(0));
        } else {
          lexemes.forEach(newSentence::add);
        }

        newParagraph.add(newSentence);
      }

      newText.add(newParagraph);
    }

    return newText;
  }




}
