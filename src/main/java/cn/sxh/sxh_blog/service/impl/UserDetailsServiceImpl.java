package cn.sxh.sxh_blog.service.impl;

import cn.sxh.sxh_blog.security.UserDetailsImpl;
import cn.sxh.sxh_blog.service.LoginService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sxh
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private LoginService loginService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDetailsImpl userDetails = (UserDetailsImpl) loginService.getUserByName(userName).getData();
        if (userDetails == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }
        User user = new User(userName,userDetails.getPassword(),userDetails.getAuthorities());
        System.out.println("detaile:"+userDetails);
        System.out.println("quanxian:"+userDetails.getAuthorities());
        return user;
    }
}
