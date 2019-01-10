package com.example.demo.controller;

import com.example.demo.dao.BookRepository;
import com.example.demo.entity.Book;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping(value = "/book")

    private List<Book> userList() {
        return bookRepository.findAll();
    }

    //添加一本书
//    @PostMapping(value = "/manager/addBook")
//    public Boolean bookAdd(@RequestParam("name") String name,
//                        @RequestParam("desc") String desc,
//                        @RequestParam("date") String date,
//                        @RequestParam("newlevel") Integer newlevel,
//                        @RequestParam("url")  String url,
//                        @RequestParam("price") double price) {
//        Book book = new Book();
//        book.setName(name);
//        book.setDescription(desc);
//        book.setReleaseDate(date);
//        book.setNewlevel(newlevel);
//        book.setPrice(price);
//        book.setUrl(url);
//        if(bookRepository.save(book)!=null){
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }
    @PostMapping(value = "/manager/addBook")
    public Boolean bookAdd(@RequestBody Map map) {
        Integer userId = Integer.parseInt(map.get("userId").toString());
        //Integer bookId = (Integer)map.get("bookId");
        String name = (String) map.get("name");
        String desc = (String) map.get("intro");
        String publication = (String) map.get("publication");
        //Double newlevel = (Double) map.get("howNew");
        String url = (String) map.get("bookImageUrl");
        Double price = Double.valueOf(map.get("price").toString());

        Book book = new Book();
        //book.setId(bookId);
        book.setUserid(userId);
        book.setName(name);
        book.setDescription(desc);
        //book.setNewlevel(newlevel);
        book.setPrice(price);
        book.setPublication(publication);
        book.setImgurl(url);
        if(bookRepository.save(book)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    //查找书籍列表
//    @GetMapping(value = "/book/{id}")
//    public Optional<Book> userFindOne(@PathVariable("id") Integer id) {
//        return bookRepository.findById(id);
//    }

    //查找书籍列表
//    @GetMapping(value = "/list/getBook")
//    public String getBookList(@RequestParam("pageNum") Integer pageNum){
//        List<Book> allBookList = bookRepository.findAll();
//        List<Book> requestBooklist = new ArrayList<>();
//        for(int i = pageNum * 12; i < allBookList.size() && i < (pageNum+1)*12; i++){
//            requestBooklist.add(allBookList.get(i));
//        }
//        return new Gson().toJson(requestBooklist);
//    }

    @PostMapping(value = "/list/getBook")
    public String getBookList(@RequestBody Map map){
        Integer pageNum = (Integer) map.get("pageNum") - 1;
        List<Book> allBookList = bookRepository.findAll();
        List<Book> requestBooklist = new ArrayList<>();
        for(int i = pageNum * 12; i < allBookList.size() && i < (pageNum+1)*12; i++){
            //System.out.println(allBookList.get(i).toString());
            requestBooklist.add(allBookList.get(i));
        }
        //System.out.println(allBookList.size());
        //System.out.println(requestBooklist.size());
        return new Gson().toJson(requestBooklist);
    }

    @PostMapping(value = "/manager/getUserBooks")
    public String getUserBook(@RequestBody Map map){
        Integer userId = Integer.parseInt(map.get("userId").toString());
        List<Book> userBooklist = bookRepository.findBookByUserid(userId);
        System.out.println(userBooklist.size());
        return new Gson().toJson(userBooklist);
    }

    @PostMapping(value = "/list/getBookCount")
    public Integer getPageCount(){
        return bookRepository.findAll().size();
    }

    //删除一本书
//    @DeleteMapping(value = "/manager/deleteBook")
//    public Boolean bookDelete(@RequestParam("bookId") Integer id) {
//        if (bookRepository.getOne(id)!=null) {
//            bookRepository.delete(bookRepository.getOne(id));
//            return true;
//        }else {
//            return false;
//        }
//    }
    @PostMapping(value = "/manager/deleteBook")
    public Boolean bookDelete(@RequestBody Map map) {
        Integer id = (Integer)map.get("bookId");
        if (bookRepository.getOne(id)!=null) {
            bookRepository.delete(bookRepository.getOne(id));
            return true;
        }else {
            return false;
        }
    }

    //更新一本书
//    @PutMapping(value = "/manager/updateBook/{id}")
//    public Boolean bookUpdate(@RequestParam("id") Integer id,
//                           @RequestParam("name") String name,
//                           @RequestParam("intro") String desc,
//                           @RequestParam("publication") String date,
//                           @RequestParam("howNew") Integer newlevel,
//                           @RequestParam("bookImageUrl")  String url,
//                           @RequestParam("price") double price) {
//
//
//        Book book = new Book();
//        book.setId(id);
//        book.setName(name);
//        book.setDescription(desc);
//        book.setNewlevel(newlevel);
//        book.setPrice(price);
//        book.setReleaseDate(date);
//        book.setUrl(url);
//        if(bookRepository.save(book)!=null){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    @PostMapping(value = "/manager/updateBook")
    public Boolean bookUpdate(@RequestBody Map map) {
        Integer id = (Integer)map.get("bookId");
        Integer userId = Integer.parseInt(map.get("userId").toString());
        String name = (String)map.get("name");
        String desc = (String)map.get("intro");
        String publication = (String)map.get("publication");
        Double newlevel = Double.valueOf(map.get("howNew").toString());
        String url = (String)map.get("bookImageUrl");
        Double price = Double.valueOf(map.get("price").toString());

        Book book = new Book();
        book.setId(id);
        book.setUserid(userId);
        book.setName(name);
        book.setDescription(desc);
        book.setNewlevel(newlevel);
        book.setPrice(price);
        book.setPublication(publication);
        book.setImgurl(url);
        if(bookRepository.save(book)!=null){
            return true;
        }
        else{
            return false;
        }
    }
}
