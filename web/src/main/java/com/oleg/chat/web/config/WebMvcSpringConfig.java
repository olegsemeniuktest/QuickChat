package com.oleg.chat.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Created by Oleg Semeniuk on 18.02.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "com.oleg.chat.web", excludeFilters = {@ComponentScan.Filter(pattern = "com.oleg.chat.web.security.*",type = FilterType.REGEX)})
public class WebMvcSpringConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/pages/**").addResourceLocations("/resources/pages/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/resources/styles/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/resources/scripts/");
    }

//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/resources/pages/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

    @Bean
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setViewClass(TilesView.class);
        return urlBasedViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles/tiles.xml");
        return tilesConfigurer;
    }

}
