package com.testgreetgo.stars.model;

public enum Discoverer {

  ARISTOTEL("Аристотель"),
  GALILEI("Галилей");


  private final String name;

  Discoverer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
