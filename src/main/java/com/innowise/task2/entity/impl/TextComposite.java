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
  public String restore() {
    StringBuilder builder = new StringBuilder();

    for (TextComponent component : children) {
      builder.append(component.restore());

      switch (component.getType()) {
        case PARAGRAPH -> builder.append("\n");
        case SENTENCE, LEXEME -> builder.append(" ");
        default -> {
        }
      }
    }

    String result = builder.toString().stripTrailing();
    logger.debug("Restored text for {}: '{}'", type, result);
    return result;
  }

  @Override
  public ComponentType getType() { return type; }
}
