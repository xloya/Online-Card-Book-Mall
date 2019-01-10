package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InfoController {

    @RequestMapping("/info")
    public String getInfo(HttpServletRequest request){

        String address = request.getParameter("address");
        System.out.print(address);
        return "index2";
    }
}
