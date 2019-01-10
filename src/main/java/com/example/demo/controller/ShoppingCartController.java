package com.example.demo.controller;

import com.example.demo.dao.BookRepository;
import com.example.demo.dao.ShoppingCartRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Book;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    //将商品加入购物车
    @PostMapping("/list/buyBook")
    public Integer addToCart(@RequestBody Map map){
        Integer buyerId = Integer.parseInt(map.get("userId").toString());
        Integer bookId = (Integer) map.get("bookId");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBookId(bookId);
        shoppingCart.setBuyerId(buyerId);
        if(shoppingCartRepository.save(shoppingCart) != null){
            if(buyerId == bookRepository.getOne(bookId).getUserid()){
                return 2;
            }
            else return 1;
        }
        else {
            return 3;
        }
    }

    //获取购物车列表
    @PostMapping("/cart/getCart")
    public String getCart(@RequestBody Map map){
        Integer buyerId = Integer.parseInt(map.get("userId").toString());
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByBuyerId(buyerId);
        List<Book> bookList = new ArrayList<Book>();
        for(ShoppingCart shoppingCart : shoppingCartList){
            if (shoppingCart.getBuyerId() == buyerId){
                bookList.add(bookRepository.findBookById(shoppingCart.getBookId()));
            }
        }
        return new Gson().toJson(bookList);
    }

    //删除购物车中物品
    @PostMapping("/cart/cancelBook")
    public String DeleteItem(@RequestBody Map map){
        Integer buyerId = Integer.parseInt(map.get("userId").toString());
        Integer bookId = (Integer) map.get("bookId");
        //System.out.println(buyerId + " " + bookId);
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByBuyerIdAndBookId(buyerId,bookId);
        //System.out.println("尺寸"+ shoppingCartList.size());
        //List<Book> bookList = new ArrayList<Book>();

        for (ShoppingCart shoppingCart : shoppingCartList){
            //bookList.add(bookRepository.findBookById(shoppingCart.getBookId()));
            shoppingCartRepository.delete(shoppingCart);
        }

        List<ShoppingCart> shoppingCartList1 = shoppingCartRepository.findByBuyerId(buyerId);
        List<Book> bookList = new ArrayList<Book>();
        for(ShoppingCart shoppingCart : shoppingCartList1){
            if (shoppingCart.getBuyerId() == buyerId){
                bookList.add(bookRepository.findBookById(shoppingCart.getBookId()));
            }
        }
        return new Gson().toJson(bookList);
    }

    //支付清空购物车
    @PostMapping("/cart/pay")
    public Boolean CartPay(@RequestBody Map map){
        Integer buyerId = Integer.parseInt(map.get("userId").toString());
        try {
            List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByBuyerId(buyerId);
            for (ShoppingCart shoppingCart : shoppingCartList) {
                shoppingCartRepository.delete(shoppingCart);
                bookRepository.delete(bookRepository.getOne(shoppingCart.getBookId()));
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @PostMapping("/getUserInfo")
    public String getUserInfo(@RequestBody Map map){
        Integer userId = Integer.parseInt(map.get("userId").toString());
        User user = userRepository.getOne(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(user.getName());
        userInfo.setUserCollege(user.getCollege());
        userInfo.setUserId(user.getId().toString());
        return new Gson().toJson(userInfo);
    }
}
