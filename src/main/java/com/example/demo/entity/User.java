package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//需要使用@Entity注解
@Entity
public class User {
    //自增ID
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    //private Integer age;
    private String password;
    private String college;
    private String email;
    private String headImageUrl;
    //需要声明无参数的构造函数
    public User(){  }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    public Integer getAge() {
//        return age;
//    }
//    public void setAge(Integer age) {
//        this.age = age;
//    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}
