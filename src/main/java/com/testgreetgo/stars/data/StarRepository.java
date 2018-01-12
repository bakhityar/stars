package com.testgreetgo.stars.data;

import com.testgreetgo.stars.model.Star;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StarRepository {
  private static final List<Star> ALL_STARS = Arrays.asList();
//      new Star (1L, "Mercury", "25.56", "56.80", "red", "Galileo"),
//      new Star (2L, "Venus", "57.56", "186.80", "white", "Kopernick"),
//      new Star (3L, "Earth", "33.56", "90.80", "orange", "Archimedus"),
//      new Star (4L, "Mars", "12.56", "72.80", "yellow", "Galileo"),
//      new Star (5L, "Jupiter", "125.56", "212.80", "red", "Ulugbek")
//  );

  public Star findByName (String name) {
    for (Star star : ALL_STARS) {
      if(star.getName().equals(name)) {
        return star;
      }
    }
    return null;
  }

  public List<Star> allStars() {
    return ALL_STARS;
  }

  public List<Star> searchByName(String name) {
    List<Star> stars = new ArrayList<>();
    for (Star star : ALL_STARS) {
      if (star.getName().contains(name)) {
        stars.add(star);
      }
    }
    return stars;
  }
}
