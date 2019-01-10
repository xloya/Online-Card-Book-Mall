package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue
    private Integer cart;
    private Integer buyerid;
    private Integer bookid;

    public Integer getBuyerId() {
        return buyerid;
    }

    public void setBuyerId(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public Integer getBookId() {
        return bookid;
    }

    public void setBookId(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getCart() {
        return cart;
    }

    public void setCart(Integer cart) {
        this.cart = cart;
    }
}
