package com.example.demo.dao;

import com.example.demo.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    @Query(value = "select * from shopping_cart where buyerId=?1 and bookId=?2", nativeQuery = true)
    List<ShoppingCart> findByBuyerIdAndBookId(Integer buyerid, Integer bookid);
    @Query(value = "select * from shopping_cart where buyerId=?1", nativeQuery = true)
    List<ShoppingCart> findByBuyerId(Integer buyerid);
}
