package com.innowise.task2.reader;

import com.innowise.task2.exception.TextException;

public interface CustomFileReader {
    String readTextFromFile(String filePath) throws TextException;
}
