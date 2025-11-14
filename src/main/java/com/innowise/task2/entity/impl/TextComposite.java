package com.innowise.task2.entity.impl;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
  private List<TextComponent> children = new ArrayList<>();
  private ComponentType type;

  private static final Logger logger = LogManager.getLogger(TextComposite.class);

  public TextComposite(ComponentType type) {
    this.type = type;
  }

  @Override
  public void add(TextComponent component) { children.add(component); }

  @Override
  public void remove(TextComponent component) { children.remove(component); }

  @Override
  public List<TextComponent> getChildren() { return new ArrayList<>(children); }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < children.size(); i++) {
      TextComponent current = children.get(i);

      if (current.getType() == ComponentType.PARAGRAPH && i == 0) {
        builder.append("\t");
      }

      builder.append(current);

      appendDelimiter(builder, current, i);
    }

    return builder.toString();
  }

  private void appendDelimiter(StringBuilder builder, TextComponent current, int index) {
    if (index == children.size() - 1) return;

    switch (current.getType()) {
      case PARAGRAPH -> builder.append("\n\t");
      case SENTENCE -> builder.append(" ");
      case WORD, SYMBOL -> {
        TextComponent next = children.get(index + 1);
        if (next.getType() == ComponentType.WORD) {
          builder.append(" ");
        }
      }
    }
  }

  @Override
  public ComponentType getType() { return type; }
}
