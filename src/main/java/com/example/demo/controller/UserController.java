package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping(value = "/user")

    private List<User> userList() {
        return userRepository.findAll();
    }

    //添加一个用户
    @PostMapping (value = "/user")
    public User userAdd(@RequestParam("name") String name,
                            @RequestParam("age") Integer age) {
        User user = new User();
        user.setName(name);
        //user.setAge(age);
        userRepository.save(user);
        return userRepository.save(user);
    }

    //查询一个用户
    @GetMapping(value = "/user/{id}")
    public Optional<User> userFindOne(@PathVariable("id") Integer id) {
        return userRepository.findById(id);
    }

    //删除一个用户
    @DeleteMapping(value = "/user/{id}")
    public void userDelete(@PathVariable("id") Integer id) {
        userRepository.delete(userRepository.getOne(id));
    }


    //更新一个用户
    @PutMapping(value = "/user/{id}")
    public User userUpdate(@PathVariable("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("age") Integer age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        //user.setAge(age);
        return userRepository.save(user);
    }

}
