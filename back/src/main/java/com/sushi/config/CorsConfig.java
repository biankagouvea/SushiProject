package com.sushi.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("forward:/index.html");
                registry.addViewController("/menu").setViewName("forward:/index.html");
                registry.addViewController("/menu/").setViewName("forward:/index.html");
                registry.addViewController("/login").setViewName("forward:/index.html");
                registry.addViewController("/login/").setViewName("forward:/index.html");
                registry.addViewController("/register").setViewName("forward:/index.html");
                registry.addViewController("/register/").setViewName("forward:/index.html");
                registry.addViewController("/admin").setViewName("forward:/index.html");
                registry.addViewController("/admin/").setViewName("forward:/index.html");
            }
        };
    }
}