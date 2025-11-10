package com.innowise.task2.service;

import com.innowise.task2.entity.TextComponent;

import java.util.List;

public interface TextService {

    int findMaxSentencesWithSameWords(TextComponent text);

    List<String> sortSentencesByLexemeCount(TextComponent text);

    TextComponent swapFirstAndLastLexeme(TextComponent text);
}
