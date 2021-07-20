package com.cy.jt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.DigestUtils;

@SpringBootApplication
public class Md5Tests {

    @Test
    void testMd5Enode(){
        //1.定义密码,盐
        String password="123456";
        String salt = "www.tedu.cn";
        //2.创建加密对象,对密码和盐进行不可逆hash加密
        //md5算法对相同内容加密时都是相同的,并且不可逆
        //md5算法对不同内容加密时可能会出现相同结果吗?(可能,但几率很小)
        String pwd =
        DigestUtils.md5DigestAsHex((password+salt).getBytes());
        //3.输出加密后的密码
        System.out.println(pwd);
        //1f6d4c93751108d06b6759c2bc353a34
        //1f6d4c93751108d06b6759c2bc353a34
        System.out.println(pwd.length());//32位
        //说明:实际项目中md5盐要存储在数据库,登陆时会基于用户名,将会用户信息查询出来,
        //并基于属于的密码和数据库查询出盐进行hash md5加密,再与数据库存储的密码进行
        //比对,比对结果正确,则允许登录
    }


}
