package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 新增对response的返回值的处理，配置了JSON注解的处理
 * 解决跨域问题springboot所需配置
 * 开发阶段要开
 */
@Configuration
public class CORSConfiguration {
    @Bean
    public WebMvcConfigurer CORSConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        //设置是否允许跨域传cookie,需要js配合，不然cookie无法带上
                        .allowCredentials(true)
                        //设置缓存时间，减少重复响应
                        .maxAge(3600);
            }


        };
    }

    //配置允许返回图片
    @Bean
    public BufferedImageHttpMessageConverter bufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}