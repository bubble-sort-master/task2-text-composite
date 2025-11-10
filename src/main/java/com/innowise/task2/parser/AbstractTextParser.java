package com.innowise.task2.parser;

import com.innowise.task2.entity.TextComponent;

public abstract class AbstractTextParser {
    protected AbstractTextParser next;

    public abstract TextComponent parse(String data);
}
