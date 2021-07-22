package com.cy.jt.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 令牌(token:ticker-通票)拦截器
 * 其中:HandlerInterceptor为Spring MVC 中的拦截器,
 * 可以在Controller方法执行之前之后执行一些动作
 * 1)Handler 处理器(Spring MVC 中将@RestController描述的类看成是处理器)
 * 2)Interceptor 拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
    /**
     * preHandle再目标Controller方法执行前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("==preHandler==");
        return false;//true表示放行,false表示拦截到请求以后,不再继续传递
    }
}
