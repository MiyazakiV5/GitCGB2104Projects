package sso.service;

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
 * 通过此对象处理登录请求
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        //1.基于用户名查询用户信息
        //Userinfo info=userDao.selectUserByUsername(username);
        //if(info==null)throw new UsernameNotFoundException();
        //2.查询用户权限信息并封装查询结果
        //List<String> as=userDao.selectUserAs(info.getId());
        String password=passwordEncoder.encode("123456");
        List<GrantedAuthority> grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        "sys:res:retrieve,sys:res:create");
        //权限信息后续要从数据库去查
        return new User(username,password,grantedAuthorities);
        //这个值返回给谁?谁调用此方法这个就返回给谁.
    }
}