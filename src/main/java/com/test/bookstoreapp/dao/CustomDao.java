package com.test.bookstoreapp.dao;

import java.math.BigDecimal;

public interface CustomDao {
    BigDecimal findBookPriceWherePublisherIs5Letters();
}
