package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //@Query(value = "select * from book where name=?1 and password=?2", nativeQuery = true)
    User findByNameAndPassword(String name, String password);
}
