package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInfo;
import com.example.demo.util.RandomCodeUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    private  String verificationCode;

    @Value("${spring.mail.username}")
    private String sender;

    //index页面
    @RequestMapping("/index2")
    public String index() {
        return "index2";
    }

    //注册页面
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody Map map) {
        String userName = (String)map.get("userName");
        String password = (String)map.get("password");
        String headImageUrl = (String)map.get("headImageUrl");
        String college = (String)map.get("college");
        String phoneNumber = (String)map.get("phoneNumber");

        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        user.setHeadImageUrl(headImageUrl);
        user.setCollege(college);

        User userEntity = userRepository.save(user);
        if(userEntity != null){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userEntity.getId().toString());
            userInfo.setUserCollege(userEntity.getCollege());
            userInfo.setUserName(userEntity.getName());
            return new Gson().toJson(userInfo);
        }
        return null;
    }

    //登录页面
//    @RequestMapping("/login")
//    public String login(){
//        return "login";
//    }

    //注册方法
    @RequestMapping("/addregister")
    @ResponseBody
    public String register(HttpServletRequest request){
        String email = request.getParameter("email");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String college = request.getParameter("college");
        //Integer age = Integer.parseInt(request.getParameter("age"));
        String inputcode = request.getParameter("inputcode");
        String headImageUrl = request.getParameter("headImageUrl");

        if (password.equals(password2)){
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            user.setCollege(college);
            //user.setAge(age);
            user.setEmail(email);
            user.setHeadImageUrl(headImageUrl);

            if(inputcode == verificationCode) {
                userRepository.save(user);
                return new Gson().toJson(user);
            }
            else {
                return "验证失败!";
            }
        }else {
            return "两次输入密码不一致";
        }
    }


    //邮箱验证
    @RequestMapping("/validate")
    @ResponseBody
    public String validate(HttpServletRequest request){
        String emailAddress = request.getParameter("email");
        RandomCodeUtils randomCodeUtils = new RandomCodeUtils();
        verificationCode = randomCodeUtils.getStringRandom();
        sendTemplateMail(emailAddress, verificationCode);
        return "已发送验证";
    }

    //登录方法
    //@ResponseBody
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody Map map){
//        String username = request.getParameter("userName");
//        String password = request.getParameter("password");
        String name = (String)map.get("userName");
        String password = (String)map.get("password");
        User userEntity = userRepository.findByNameAndPassword(name,password);
        if (userEntity !=null){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userEntity.getId().toString());
            userInfo.setUserCollege(userEntity.getCollege());
            userInfo.setUserName(userEntity.getName());
            return new Gson().toJson(userInfo);
        }else {
            return null;
        }
    }


    public void sendTemplateMail(String recipient, String randomcode){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(recipient);
            helper.setSubject("Vue书店验证邮件");
            Context context = new Context();
            context.setVariable("code", randomcode);
            String emailContent = templateEngine.process("emailTemplate", context);
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException("Messaging  Exception !", e);
        }
        javaMailSender.send(message);
    }


}
