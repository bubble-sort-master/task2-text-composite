package com.innowise.task2.reader.impl;

import com.innowise.task2.exception.TextException;
import com.innowise.task2.reader.CustomFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomFileReaderImpl implements CustomFileReader {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String readTextFromFile(String filePath) throws TextException {
        Path path = Paths.get(filePath);
        logger.info("Attempting to read file from path: {}", filePath);

        try {
            String content = Files.readString(path);
            logger.info("Successfully read file: {} ({} characters)", filePath, content.length());
            return content;
        } catch (IOException e) {
            logger.error("Failed to read file: {}. Exception: {}", filePath, e.toString());
            throw new TextException("Error while reading file " + filePath, e);
        }
    }
}
