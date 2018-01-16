package com.testgreetgo.stars.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Discoverers {
  private List<String> discoverers;

  public Discoverers() {
    discoverers = Arrays.asList("Аристотель", "Галилей", "Коперник", "Галле", "Гершель");
  }

  public List<String> getDiscoverers() {
    return discoverers;
  }

  public void setDiscoverers(List<String> discoverers) {
    this.discoverers = discoverers;
  }
}
