package com.innowise.task2.entity.impl;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> children = new ArrayList<>();
    private ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) { children.add(component); }

    @Override
    public void remove(TextComponent component) { children.remove(component); }

    @Override
    public List<TextComponent> getChildren() { return Collections.unmodifiableList(children); }

    @Override
    public String restore() {
        StringBuilder builder = new StringBuilder();

        for (TextComponent component : children) {
            builder.append(component.restore());

            switch (component.getType()) {
                case PARAGRAPH -> builder.append("\n");
                case SENTENCE -> builder.append(" ");
                case LEXEME -> builder.append(" ");
                default -> {
                }
            }
        }

        return builder.toString().stripTrailing();
    }


    @Override
    public ComponentType getType() { return type; }
}
