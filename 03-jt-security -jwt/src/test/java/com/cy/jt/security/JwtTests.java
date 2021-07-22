package com.cy.jt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTests {
    private String secret="AAABBBCCCDDDEEE";
    /**测试创建和解析token的过程*/
    @Test
    void testCreateAndParseToken(){
        //1.创建一个token(包含三部分信息:头信息,负载信息,签名信息)
        //1.1获取登录认证信息(例如用户名,权限)
        Map<String,Object> map=new HashMap<>();
        map.put("username","jack");
        map.put("permissions","sys:res:create,sys:res:retrieve");
        //1.2基于JWT规范创建token对象(令牌-通票ticket)
        //Java中日历对象,通过此对象可以对日期进行加减运算
        Calendar calendar = Calendar.getInstance();
        //再当前时间的基础上加30分钟
        calendar.add(Calendar.MINUTE,30);
        //获取Date对象,让此对象作为令牌的失效时间
        Date expirationTime = calendar.getTime();
        String token= Jwts.builder()
                //自定义负载信息(存储登录用户信息)
                .setClaims(map)
                //失效时间(标准负载信息)
                //.setExpiration(new Date(System.currentTimeMillis()+30*1000))
                .setExpiration(expirationTime)
                //签发时间
                .setIssuedAt(new Date())
                //签名加密算法以及密钥盐
                .signWith(SignatureAlgorithm.HS256,secret)
                //生成token
                .compact();
        System.out.println("token="+token);

        //2.解析token内容
        Claims body = Jwts.parser()//获取解析器对象
                //设置解析时使用的密钥
                .setSigningKey(secret)
                //获取token中的负载
                .parseClaimsJws(token)
                //获取具体负载内容
                .getBody();
        System.out.println(body);
    }
}