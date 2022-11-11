package com.test.bookstoreapp.projections;

import java.math.BigDecimal;

public class BookOnlyPrice {
    BigDecimal price;

    public BookOnlyPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookOnlyPrice{" +
                "price=" + price +
                '}';
    }
}
