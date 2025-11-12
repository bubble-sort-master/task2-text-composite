package com.innowise.task2.parser;

import com.innowise.task2.entity.TextComponent;

public abstract class AbstractTextParser {private AbstractTextParser next;

  public void setNext(AbstractTextParser next) {
    this.next = next;
  }

  protected AbstractTextParser getNext() {
    return next;
  }

  public abstract TextComponent parse(String data);
}
