package com.cy.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class,args);
        encodePwd();
    }
    //通过Spring Security 中的BCryptPasswordEncoder基于明文创建一个密文
    static void encodePwd(){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String password="123456";//明文
        String newPwd=encoder.encode("123456");
        System.out.println(newPwd);
    }
}