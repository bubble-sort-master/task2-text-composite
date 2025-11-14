package com.innowise.task2.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    void remove(TextComponent component);
    List<TextComponent> getChildren();
    ComponentType getType();
    String toString();
}
