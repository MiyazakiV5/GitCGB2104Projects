package com.cy.jt.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 可以将这里的Controller看成是系统内部的一个资源对象,我们
 * 要求访问此对象中的方法时需要进行权限检查.
 */
@RestController
public class ResourceController {//UserController,CategoryController,ProductController,....
    /**添加操作
     * @PreAuthorize 注解由SpringSecurity框架提供,用于描述方法,此注解描述
     * 方法以后,再访问方法首先要进行权限检测
     */
    //假如登录用户具备admin这个角色可以访问
    //@PreAuthorize("hasRole('admin')")
    //登录用户具备sys:res:create权限才可访问资源
    @PreAuthorize("hasAuthority('sys:res:create')")
    @RequestMapping("/doCreate")
    public String doCreate(HttpServletResponse response){

        return "create resource (insert data) ok";
    }
    /**查询操作*/
    @PreAuthorize("hasAuthority('sys:res:retrieve')")
    @RequestMapping("/doRetrieve")
    public String doRetrieve(){//Retrieve 表示查询
        return "query resource (select data) ok";
    }
    /**修改操作*/
    @PreAuthorize("hasAuthority('sys:res:update')")
    @RequestMapping("/doUpdate")
    public String doUpdate(){
        return "update resource (update data) ok";
    }
    /**删除操作*/
    @PreAuthorize("hasAuthority('sys:res:delete')")
    @RequestMapping("/doDelete")
    public String doDelete(){
        return "delete resource (dalete data) ok";
    }


    /**
     * 获取登录用户信息?
     * 1)用户登录成功以后信息存储到了哪里
     * 2)如何获取用户登录信息
     * @return
     */
    @GetMapping("/doGetUser")
    public String doGetUser(){
        //从Session中获取用户认证信息
        //1)Authentication 认证对象(封装了登录用户信息的对象)
        //2)SecurityContextHolder 持有登录状态的信息的对象(底层可通过session获取用户信息)
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        //基于认证对象获取用户身份信息
        User principal = (User)authentication.getPrincipal();
        System.out.println("principal.class="+principal.getClass());
        return principal.getUsername()+":"+principal.getAuthorities();
    }

}