package com.cy.jt.security.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 假如用户没有认证,就去访问需要认证才可以访问的资源,系统
 * 底层会抛出一个异常AuthenticationEntryPoint,系统默认
 * 对比此异常的处理方式是跳转到登陆页面,加入现在我们不是要
 * 跳转到登录页面,而是要返回一个json格式的字符串,则需要
 * 自己定义AuthenticationEntryPoint接口的实现类
 */
public class DefaultAuthenticationEntryPoint
        implements AuthenticationEntryPoint {
    /**
     * 当系统出现AuthenticationEntryPoint异常时,
     * 会自动调用此方法(commence-开始,着手)
     * @param httpServletRequest    请求对象
     * @param httpServletResponse   响应对象
     * @param e                     异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        //方案1:重定向
        //httpServletResponse.sendRedirect("http://www.tedu.cn");
        //方案2:假如访问被拒绝了向客户端响应一个json格式的字符串
        //2.1设置响应数据的编码
        httpServletResponse.setCharacterEncoding("utf-8");
        //2.2告诉浏览器响应数据的内容类型以及编码
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //2.3获取输出流对象
        PrintWriter out=httpServletResponse.getWriter();
        //2.4将数据输出到客户端
        //2.4.1封装数据
        Map<String,Object> map=new HashMap<>();
        map.put("state",HttpServletResponse.SC_UNAUTHORIZED);//SC_FORBIDDEN的值是401
        map.put("message","请先登录再访问");
        //2.4.2将数据转换为json格式字符串
        String jsonStr=
                new ObjectMapper().writeValueAsString(map);
        //2.4.3输出数据
        out.println(jsonStr);
        out.flush();
    }
}
