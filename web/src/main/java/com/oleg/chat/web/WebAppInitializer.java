package com.oleg.chat.web;

import com.google.common.base.Preconditions;
import com.oleg.chat.data.config.DataModuleSpringConfig;
import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.web.config.SecuritySpringConfig;
import com.oleg.chat.web.config.WebMvcSpringConfig;
import org.apache.log4j.helpers.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.Assert;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;

/**
 * Created by Oleg Semeniuk on 18.02.2015.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private Logger log = LoggerFactory.getLogger(WebAppInitializer.class);

    private static final String PROFILES_PROPERTY_NAME = "active.profiles";
    private static final String PROFILES_CONFIG_FILE = "properties/active_profiles.properties";

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
        rootContext.getEnvironment().setDefaultProfiles(DataProfiles.MONGO_DB);

        try {
            rootContext.getEnvironment().setActiveProfiles(loadProfilesConfig());
        } catch (Exception e) {
            log.warn("Profiles was not loaded: {}", e.getMessage());
        }

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

    private String[] loadProfilesConfig() throws Exception {
        Properties properties = new Properties();
        InputStream configStream = getClass().getClassLoader().getResourceAsStream(PROFILES_CONFIG_FILE);
        Assert.notNull(configStream, "Configuration file of profiles not found.");
        properties.load(configStream);
        Assert.state(properties.containsKey(PROFILES_PROPERTY_NAME), "There are no property for active profiles in the config file.");
        String[] profiles = properties.getProperty(PROFILES_PROPERTY_NAME).split("\\s*,\\s*");
        return profiles;
    }

}
