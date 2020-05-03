package cn.sxh.sxh_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sxh
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/index").setViewName("/admin/index");
        registry.addViewController("/login_page").setViewName("/admin/login");
        registry.addViewController("/index").setViewName("/front/index");
    }
}
