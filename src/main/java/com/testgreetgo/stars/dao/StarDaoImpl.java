package com.testgreetgo.stars.dao;

import com.testgreetgo.stars.model.Star;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StarDaoImpl implements StarDao {

  @Autowired
  private SessionFactory sessionFactory;


  @Override
  public List<Star> searchByName(String q) {
    Session session = sessionFactory.openSession();
    Query query = session.createQuery("from Star S where S.name like :q");
    query.setParameter("q", "%"+q+"%");
    List<Star> stars = query.list();
    return stars;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Star> findAll() {
    Session session = sessionFactory.openSession();
    List<Star> stars = session.createCriteria(Star.class).list();
    session.close();
    return stars;
  }

  @Override
  public Star findById(Long id) {
    Session session = sessionFactory.openSession();
    Star star = session.get(Star.class, id);
    session.close();
    return star;
  }

  @Override
  public void save(Star star) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(star);
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public void delete(Star star) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.delete(star);
    session.getTransaction().commit();
    session.close();

  }
}
