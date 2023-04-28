package study.hitchhiking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.hitchhiking.interceptor.TokenInterceptor;

//自定义配置类
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置拦截路径，排除路径，优先级等
        registry.addInterceptor(new TokenInterceptor())
                .excludePathPatterns("/login/**").order(11)
                .excludePathPatterns("/login.html").order(12)
                .excludePathPatterns("/CLogin.html").order(13)
                .addPathPatterns("/index.html")
                .addPathPatterns("/index/**")
                .addPathPatterns("/user/**")
                .addPathPatterns("/userCenter/**")
                .addPathPatterns("/comment/**")
                .addPathPatterns("/orders/**")
                .addPathPatterns("/car/**")
                .addPathPatterns("/manage/**");

    }
}

