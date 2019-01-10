package com.example.demo.dao;


import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBookByUserid(Integer userId);
    Book findBookById(Integer id);
}
