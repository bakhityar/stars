package com.testgreetgo.stars.dao;

import com.testgreetgo.stars.model.Discoverers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class DiscoverersDaoImpl implements DiscoverersDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  @SuppressWarnings("unchecked")
  public List<Discoverers> findAll() {
    Session session = sessionFactory.openSession();
    List<Discoverers> discoverers = session.createCriteria(Discoverers.class).list();
    session.close();
    return discoverers;
  }

  @Override
  public Discoverers findById(Long id) {
    Session session = sessionFactory.openSession();
    //Собираем все объекты типа Discoverers и делаем из него список
    Discoverers discoverers = session.get(Discoverers.class, id);
    session.close();
    return discoverers;
  }

  @Override
  public void save(Discoverers discoverer) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    //Если объект существует Update, если нет то Save(т.е. create )
    session.saveOrUpdate(discoverer);
    session.getTransaction().commit();
    session.close();

  }

  @Override
  public void delete(Discoverers discoverer) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.delete(discoverer);
    session.getTransaction().commit();
    session.close();

  }
}
