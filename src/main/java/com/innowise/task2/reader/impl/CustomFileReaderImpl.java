package com.innowise.task2.reader;

import com.innowise.task2.exception.TextException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderImpl implements FileReader {

    @Override
    public String readTextFromFile(String filePath) throws TextException {
        Path path = Paths.get(filePath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new TextException("Error while reading file " + filePath, e);
        }
    }
}
