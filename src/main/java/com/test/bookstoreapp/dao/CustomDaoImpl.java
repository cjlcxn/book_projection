package com.test.bookstoreapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CustomDaoImpl implements CustomDao {

    private EntityManager entityManager;

    @Autowired
    public CustomDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BigDecimal findBookPriceWherePublisherIs5Letters() {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("SELECT " +
                "book.price " +
                "FROM " +
                "Book book " +
                "WHERE " +
                "LENGTH(book.publisher) = 5");
        try {
            List<BigDecimal> price = query.getResultList();
            System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
            System.out.println(price);
            return price.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
