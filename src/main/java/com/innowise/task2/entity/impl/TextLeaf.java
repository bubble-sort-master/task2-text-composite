package com.innowise.task2.entity.impl;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final String value;
    private final ComponentType type;

    public TextLeaf(String value, ComponentType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) { throw new UnsupportedOperationException(); }

    @Override
    public void remove(TextComponent component) { throw new UnsupportedOperationException(); }

    @Override
    public List<TextComponent> getChildren() { return Collections.emptyList(); }

    @Override
    public String restore() { return value; }

    @Override
    public ComponentType getType() { return type; }
}
