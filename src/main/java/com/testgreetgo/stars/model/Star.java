package com.testgreetgo.stars.model;

public class Star {
  private String name;
  private String xCoord;
  private String yCoord;
  private String color;
  private String discoverer;

  public Star(String name, String xCoord, String yCoord, String color, String discoverer) {
    this.name = name;
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.color = color;
    this.discoverer = discoverer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getxCoord() {
    return xCoord;
  }

  public void setxCoord(String xCoord) {
    this.xCoord = xCoord;
  }

  public String getyCoord() {
    return yCoord;
  }

  public void setyCoord(String yCoord) {
    this.yCoord = yCoord;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getDiscoverer() {
    return discoverer;
  }

  public void setDiscoverer(String discoverer) {
    this.discoverer = discoverer;
  }
}
