package com.oleg.chat.web;

import com.oleg.chat.data.config.DataModuleSpringConfig;
import com.oleg.chat.web.config.SecuritySpringConfig;
import com.oleg.chat.web.config.WebMvcSpringConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by Oleg Semeniuk on 18.02.2015.
 */
public class WebAppInitializer implements WebApplicationInitializer {

//        @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(DataModuleSpringConfig.class, SecuritySpringConfig.class);
//
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//        dispatcherServlet.register(WebMvcSpringConfig.class);
//
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    initFilters(servletContext);
//    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext rootContext = createApplicationContext();
        servletContext.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        initFilters(servletContext);
    }

    private WebApplicationContext createApplicationContext() {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(DataModuleSpringConfig.class, SecuritySpringConfig.class, WebMvcSpringConfig.class);
        return rootContext;
    }

    private void initFilters(ServletContext servletContext) {
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

//        Encoding filter to support cyrillic symbols
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic encodingFilterDynamic = servletContext.addFilter("encoding-filter", encodingFilter);
        encodingFilterDynamic.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

//        Security filter
        dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR);
        DelegatingFilterProxy springSecurityFilter = new DelegatingFilterProxy();
        FilterRegistration.Dynamic springSecurityFilterDynamic = servletContext.addFilter("springSecurityFilterChain", springSecurityFilter);
        springSecurityFilterDynamic.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }

}
