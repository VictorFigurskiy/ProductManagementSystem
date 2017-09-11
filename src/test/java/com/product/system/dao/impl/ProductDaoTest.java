package com.product.system.dao.impl;

import com.product.system.dao.ProductDao;
import com.product.system.dao.impl.config.TestConfiguration;
import com.product.system.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Sonikb on 06.09.2017.
 */
@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ProductDaoTest {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private ProductDao productDao;
    private Product product;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        product = mock(Product.class);
        productDao = new ProductDaoImpl(sessionFactory);
    }

    @After
    public void tearDown() throws Exception {
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testGetById() throws Exception {
        when(session.get(Product.class, 1)).thenReturn(product);
        when(product.getId()).thenReturn(1);

        assertEquals(product, productDao.getById(Product.class, 1));
        assertEquals(1, (long) product.getId());

        verify(session, atLeast(1)).get(Product.class, 1);
        verify(product, times(1)).getId();
    }

    @Test(expected = NullPointerException.class)
    public void testGetByIdNull() throws Exception {
        when(session.get(Product.class, 0)).thenReturn(null);
        when(product.getId()).thenThrow(new NullPointerException());

        assertEquals(null, productDao.getById(Product.class, 0));

        verify(session, atMost(2)).get(Product.class, 0);
        product.getId();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Product> mockList = mock(List.class);
        when(mockList.get(0)).thenReturn(product);
        when(mockList.get(1)).thenReturn(null);

        Criteria criteria = mock(Criteria.class);

        when(session.createCriteria(Product.class)).thenReturn(criteria);
        when(criteria.list()).thenReturn(mockList);

        assertEquals(mockList, productDao.getAll(Product.class));
        assertFalse(mockList.isEmpty());
        assertNull("Здесь должен быть null", mockList.get(1));
        assertNotNull(mockList.get(0));
        assertEquals(product, mockList.get(0));

        verify(criteria, times(1)).list();
        verify(mockList, atLeast(2)).get(0);
    }

    @Test
    public void testSave() throws Exception {
        when(product.getId()).thenReturn(1);

        doAnswer(invocation -> {
            Product product1 = invocation.getArgument(0);
            assertEquals((Integer) 1, product1.getId());
            return null;
        }).when(session).save(product);

        productDao.save(product);

        verify(session, atLeastOnce()).save(product);
    }

    @Test
    public void testSaveSpy() throws Exception {
        ProductDao dao = new ProductDaoImpl(sessionFactory);
        dao = spy(dao);

        doAnswer(invocation -> null).when(dao).save(product);

        dao.save(product);

        verify(dao, atLeastOnce()).save(product);
    }

    @Test
    public void testUpdate() throws Exception {
        doAnswer(invocation -> null).when(session).update(product);
        productDao.update(product);
        verify(session, timeout(100)).update(product);
    }

    @Test
    public void testRemove() throws Exception {
        doAnswer(invocation -> null).when(session).delete(product);
        productDao.remove(product);
        verify(session, times(1)).delete(any());
    }

}