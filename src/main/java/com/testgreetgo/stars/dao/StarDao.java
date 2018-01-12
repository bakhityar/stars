package com.testgreetgo.stars.dao;

import com.testgreetgo.stars.model.Star;

import java.util.List;

public interface StarDao {
  List<Star> findAll();
  Star findById(Long id);
  void save(Star star);
  void delete(Star star);
}
