package com.product.system.services;

import com.product.system.dao.ProductDao;
import com.product.system.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sonikb on 08.08.2017.
 */
@Service
public class ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional(readOnly = true)
    public ProductEntity getById(Integer id) {
        return productDao.getById(ProductEntity.class, id);
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getAll() {
        return productDao.getAll(ProductEntity.class);
    }

    @Transactional
    public void save(ProductEntity entity) {
        productDao.save(entity);
    }

    @Transactional
    public void update(ProductEntity entity) {
        productDao.update(entity);
    }

    @Transactional
    public void remove(ProductEntity entity) {
        productDao.remove(entity);
    }
}
