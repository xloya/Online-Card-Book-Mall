package com.example.demo.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RecieveController {

    @PostMapping(value = "/list/buyBookasd")
    public Integer getInfo(@RequestBody Map map){
        Integer bookId = (Integer) map.get("bookId");
//        Integer sd = Integer.parseInt(request.getParameter("bookId"));
        System.out.println("asdfasfasdfas");
        return bookId;
    }
}