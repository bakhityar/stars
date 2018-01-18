package com.testgreetgo.stars.service;

import com.testgreetgo.stars.dao.DiscoverersDao;
import com.testgreetgo.stars.model.Discoverers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoverersServiceImpl implements DiscoverersService{

  //Все в сервисе реализуется через DAO и его Autowired

  @Autowired
  private DiscoverersDao discoverersDao;

  @Override
  public List<Discoverers> findAll() {
    return discoverersDao.findAll();
  }

  @Override
  public Discoverers findById(Long id) {
    return discoverersDao.findById(id);
  }

  @Override
  public void save(Discoverers discoverer) {
    discoverersDao.save(discoverer);

  }

  @Override
  public void delete(Discoverers discoverer) {
    discoverersDao.delete(discoverer);

  }
}
