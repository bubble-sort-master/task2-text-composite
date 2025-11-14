package com.innowise.task2.service;

import com.innowise.task2.entity.TextComponent;

import java.util.List;

public interface TextService {

    int findMaxSentencesContainingSameWord(TextComponent text);

    List<String> sortSentencesByWordCount(TextComponent text);

    TextComponent swapFirstAndLastWord(TextComponent text);
}
