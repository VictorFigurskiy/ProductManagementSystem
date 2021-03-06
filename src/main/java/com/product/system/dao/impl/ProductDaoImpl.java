package com.product.system.dao.impl;

import com.product.system.dao.ProductDao;
import com.product.system.entity.ProductEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sonikb on 08.08.2017.
 */
@Repository
public class ProductDaoImpl extends AbstractGeneralDaoImpl<Integer,ProductEntity> implements ProductDao {

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
