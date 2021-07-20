package com.cy.jt.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 由@Configuration注解描述的类为spring中的配置类,配置类会在spring
 * 工程启动时优先加载,在配置类中通常会对第三方资源进行初始配置.
 */
@Configuration
public class SecurityConfig {
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