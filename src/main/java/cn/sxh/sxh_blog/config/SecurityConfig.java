package cn.sxh.sxh_blog.config;

import cn.sxh.sxh_blog.security.UserAuthenticationAccessDeniedHandler;
import cn.sxh.sxh_blog.security.UserLoginAuthenticationFailureHandler;
import cn.sxh.sxh_blog.security.UserLoginAuthenticationSuccessHandler;
import cn.sxh.sxh_blog.security.UserLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private UserLoginAuthenticationSuccessHandler userLoginAuthenticationSuccessHandler;
    @Resource
    private UserLoginAuthenticationFailureHandler userLoginAuthenticationFailureHandler;
    @Resource
    private UserLogoutSuccessHandler userLogoutSuccessHandler;
    @Resource
    private UserAuthenticationAccessDeniedHandler userAuthenticationAccessDeniedHandler;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()//设置弹出层
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/fonts/**", "/html/**", "/images/**", "/js/**", "/lib/**","/index","/api/**")//静态资源等不需要验证
                .permitAll()//不需要身份认证
                .anyRequest().authenticated()//其他路径必须验证身份
                .and()
                .formLogin()
                .loginPage("/login_page")//登录页面
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(userLoginAuthenticationFailureHandler)//验证失败处理
                .successHandler(userLoginAuthenticationSuccessHandler)//验证成功处理
                .permitAll()//登录页面不需要验证
                .and()
                .logout()
                .logoutSuccessHandler(userLogoutSuccessHandler)//登出处理
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(userAuthenticationAccessDeniedHandler);//无权限时的处理
    }


}
