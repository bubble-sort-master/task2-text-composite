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
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);

        TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
        sentence1.add(createLexeme("Hello", ComponentType.WORD));
        sentence1.add(createLexeme("world", ComponentType.WORD));

        TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
        sentence2.add(createLexeme("world", ComponentType.WORD));
        sentence2.add(createLexeme("privet", ComponentType.WORD));

        TextComposite sentence3 = new TextComposite(ComponentType.SENTENCE);
        sentence3.add(createLexeme("unique", ComponentType.WORD));

        paragraph.add(sentence1);
        paragraph.add(sentence2);
        paragraph.add(sentence3);
        text.add(paragraph);

        int result = service.findMaxSentencesContainingSameWord(text);
        assertEquals(2, result);
    }

    @Test
    public void testSortSentencesByLexemeCount() {
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);

        TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
        sentence1.add(createLexeme("One", ComponentType.WORD));

        TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
        sentence2.add(createLexeme("Two", ComponentType.WORD));
        sentence2.add(createLexeme("words", ComponentType.WORD));

        TextComposite sentence3 = new TextComposite(ComponentType.SENTENCE);
        sentence3.add(createLexeme("Three", ComponentType.WORD));
        sentence3.add(createLexeme("lexemes", ComponentType.WORD));
        sentence3.add(createLexeme("here", ComponentType.WORD));

        paragraph.add(sentence3);
        paragraph.add(sentence1);
        paragraph.add(sentence2);
        text.add(paragraph);

        List<String> sorted = service.sortSentencesByLexemeCount(text);
        assertEquals(List.of("One", "Two words", "Three lexemes here"), sorted);
    }



    private TextComponent createLexeme(String word, ComponentType type) {
        TextComposite lexeme = new TextComposite(ComponentType.LEXEME);
        lexeme.add(new TextLeaf(word, type));
        return lexeme;
    }
}
