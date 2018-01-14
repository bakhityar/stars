package com.testgreetgo.stars.model;

public enum Color {

  BLUE("Голубой","#0099ff"),
  WHITEBLUE("Бело-голубой","#ccebff"),
  WHITE("Белый","white"),
  WHITEYELLOW("Желто-белый","#ffffcc"),
  YELLOW("Желтый","yellow"),
  ORANGE("Оранжевый","orange"),
  RED("Красный","red");

  private final String name;
  private final String hexCode;

  Color(String name, String hexCode) {
    this.name = name;
    this.hexCode = hexCode;
  }

  public String getName() {
    return name;
  }

  public String getHexCode() {
    return hexCode;
  }
}
