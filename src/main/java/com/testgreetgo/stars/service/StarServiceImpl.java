package com.testgreetgo.stars.service;

import com.testgreetgo.stars.dao.StarDao;
import com.testgreetgo.stars.model.Star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarServiceImpl implements StarService {

  @Autowired
  private StarDao starDao;

  @Override
  public List<Star> findAll() {
    return starDao.findAll();
  }

  @Override
  public Star findById(Long id) {
    return null;
  }

  @Override
  public void save(Star star) {
    starDao.save(star);

  }

  @Override
  public void delete(Star star) {
    starDao.delete(star);
  }
}
