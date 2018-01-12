package com.testgreetgo.stars.service;

import com.testgreetgo.stars.model.Star;

import java.util.List;

public interface StarService {
  List<Star> findAll();
  Star findById(Long id);
  void save(Star star);
  void delete(Star star);
}
