package com.testgreetgo.stars.dao;

import com.testgreetgo.stars.model.Discoverers;

import java.util.List;

public interface DiscoverersDao {
  List<Discoverers> findAll();
  Discoverers findById(Long id);
  void save(Discoverers discoverer);
  void delete(Discoverers discoverer);
}
