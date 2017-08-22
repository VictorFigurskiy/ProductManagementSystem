package com.product.system.dao.impl;

import com.product.system.dao.GeneralDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Sonikb on 08.08.2017.
 */
public abstract class AbstractGeneralDaoImpl<K extends Serializable, V> implements GeneralDao<K, V> {


//    private Class<V> persistentClass;
//
//    @SuppressWarnings("unchecked")
//    public AbstractGeneralDaoImpl() {   // This how we can represent this Class<? extends V> entityClass in another way.
//        this.persistentClass = (Class<V>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//    }


    private SessionFactory sessionFactory;

    public AbstractGeneralDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public V getById(Class<? extends V> entityClass, K id) {
        return getSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<V> getAll(Class<? extends V> entityClass) {
        return (List<V>) getSession().createCriteria(entityClass).list();
    }

    public void save(V entity) {
        getSession().save(entity);
    }

    public void update(V entity) {
        getSession().update(entity);
    }

    public void remove(V entity) {
        getSession().delete(entity);
    }
}
