package com.cy.jt.security.config;
import com.cy.jt.security.config.handler.DefaultAccessDeniedExceptionHandler;
import com.cy.jt.security.config.handler.DefaultAuthenticationEntryPoint;
import com.cy.jt.security.config.handler.DefaultAuthenticationFailureHandler;
import com.cy.jt.security.config.handler.DefaultAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

/**
 * 由@Configuration注解描述的类为spring中的配置类,配置类会在spring
 * 工程启动时优先加载,在配置类中通常会对第三方资源进行初始配置.
 *
 * @EnableGlobalMethodSecurity 注解由SpringSecurity提供,用于
 * 描述权限配置类,告诉系统底层在启动时,进行访问权限的初始化
 * 1)Enable-启用
 * 2)Global-全局
 * 3)Method-方法
 * 4)Security-安全
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**对http请求的安全控制进行配置*/

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //super.configure(http)
        //1.关闭跨域攻击
        http.csrf().disable();
        //2.配置登录url(登陆表单使用哪个页面)
        http.formLogin().loginPage("/login.html")//登录页面
                        .loginProcessingUrl("/login")//与form表单中的action值相同
                        //.usernameParameter("username")//与form表单中input元素的name属性相同
                        //.passwordParameter("password")
                        //.successForwardUrl("/index.html");//请求转发
                        .defaultSuccessUrl("/index.html");//登陆成功的url(redirect)
                        //.successHandler(new RedirectAuthenticationSuccessHandler("http://www.tedu.cn"));
                        //.failureHandler()
                        //.successForwardUrl("/index.html");//get
                        //.failureUrl("/login.html?error");//登陆失败(默认)
                        //.successHandler(new DefaultAuthenticationSuccessHandler())
                        //.failureHandler(new DefaultAuthenticationFailureHandler());
        //推出操作
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html?logout");
        //设置需要认证与拒绝访问的异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(
                new DefaultAuthenticationEntryPoint())
                .accessDeniedHandler(
                new DefaultAccessDeniedExceptionHandler());
        //3.放行登录url(不需要验证就可以访问)
        http.authorizeRequests()
                .antMatchers("/login.html","/images/**")//这里写你要放行的资源
                .permitAll()//允许直接访问
                .anyRequest().authenticated();//除了以上资源必须认证才可访问
    }
    /**
     * 定义SpringSecurity密码加密对象
     * @Bean 注解通常会在@Configuration注解描述的类中描述方法,
     * 用于告诉spring框架这个方法的返回值会交给spring管理,并spring
     * 管理的这个对象起个默认的名字,这个名字与方法名相同,当然也可以通过
     * @Bean注解起名字
     */
    @Bean //对象名默认为方法名
    //@Bean("bcryptPasswordEncoder")//bean对象名字为bcryptPasswordEncoder
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}