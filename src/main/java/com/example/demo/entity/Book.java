package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    //自增Id
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userid;
    private String name;
    private String description;
    private String publication;
    private Double newlevel;
    private String imgurl;
    private Double price;


    public Book(){

    }
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Double getNewlevel() {
        return newlevel;
    }

    public void setNewlevel(Double newlevel) {
        this.newlevel = newlevel;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer uerid) {
        this.userid = uerid;
    }
}
