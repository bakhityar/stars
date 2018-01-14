package com.testgreetgo.stars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Star {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 2, max = 30, message = "Должно быть >2, но <30 символов")
  private String name;
  @NotNull
  @Size(min = 2, max = 10, message = "Должно быть >2, но <30 символов")
  private String xCoord;
  @NotNull
  @Size(min = 2, max = 10, message = "Должно быть >2, но <30 символов")
  private String yCoord;
  private String color;
  private String discoverer;

  public Star() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
