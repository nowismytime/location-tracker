package com.nearbuy.location.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.nearbuy.location.dao", "com.nearbuy.location.config"})
@PropertySource(ignoreResourceNotFound = false, value ={"classpath:env.properties"})
public class ApplicationConfigTest {

    
    
}
