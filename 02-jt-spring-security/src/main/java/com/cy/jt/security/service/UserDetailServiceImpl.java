package com.cy.jt.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明:学框架首先要学的就是规范,规则.
 * 定义UserDetailsService接口的具体实现,在这个实现定义用户登录逻辑
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    /**
     * 当我们执行登录操作时,底层会通过过滤器等对象,调用这个方法.
     * @param username 这个参数为页面输出的用户名
     * @return 一般是从数据库基于用户名查询到的用户信息
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //1.基于用户名从数据库查询用户信息
        //User user=userMapper.selectUserByUsername(username);
        if(!"jack".equals(username))//假设这是从数据库查询的信息
            throw new UsernameNotFoundException("user not exists");
        //2.将用户信息封装到UserDetails对象中并返回
        //假设这个密码是从数据库查询出来的
        String encodedPwd=passwordEncoder.encode("123456");
        //假设这个权限信息也是从数据库查询到的
        //假如分配权限的方式是角色,编写字符串时用"ROLE_"做前缀
        List<GrantedAuthority> grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        "ROLE_admin,ROLE_normal,sys:res:retrieve,sys:res:create");

        User user=new User(username,encodedPwd,grantedAuthorities);
        return user;
    }
}