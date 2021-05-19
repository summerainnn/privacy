package summerain.privacy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 9:42 下午
 */

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main.html").setViewName("main");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/toRegister").setViewName("register");
    }
}
