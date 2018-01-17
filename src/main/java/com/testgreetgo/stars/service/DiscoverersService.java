package com.testgreetgo.stars.service;

import com.testgreetgo.stars.model.Discoverers;

import java.util.List;

public interface DiscoverersService {
  List<Discoverers> findAll();
  Discoverers findById(Long id);
  void save(Discoverers discoverer);
  void delete(Discoverers discoverer);
}
