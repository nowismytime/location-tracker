package com.nearbuy.location.config;


import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean(name="restOperations")
    public RestTemplate getRestTemplate() {
        RestTemplate restOperations = new RestTemplate();
        
        restOperations.setErrorHandler(new DefaultResponseErrorHandler() {
            
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                if(statusCode.equals(HttpStatus.BAD_REQUEST) || statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                    return false;
                }
                return super.hasError(response);
            }
            
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                super.handleError(response);
            }
        });
        return restOperations;
    }
    
    @Bean
    public PropertySourcesPlaceholderConfigurer getPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
