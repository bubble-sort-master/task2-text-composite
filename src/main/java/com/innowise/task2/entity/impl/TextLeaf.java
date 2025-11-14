package com.innowise.task2.entity.impl;

import com.innowise.task2.entity.ComponentType;
import com.innowise.task2.entity.TextComponent;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
  private final char value;
  private final ComponentType type;

  public TextLeaf(char value, ComponentType type) {
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
  public String toString() { return String.valueOf(value); }

  @Override
  public ComponentType getType() { return type; }
}
