
package com.alivc.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class TokenConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    PackInterceptor packInterceptor() {
        return new PackInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
        .addPathPatterns("/live/**")
        .addPathPatterns("/vod/**")
        .addPathPatterns("/longVideo/**")
        .addPathPatterns("/user/updateUser")
        .addPathPatterns("/console/vod/**")
        .addPathPatterns("/console/longVideo/**");

        registry.addInterceptor(packInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/vodcallback/callback");

    }
}