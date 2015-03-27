package com.verifone.ums;

import com.verifone.ums.rest.CompanyResource;
import com.verifone.ums.rest.UserResource;
import com.verifone.ums.servlet.RequestBenchLogger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Pavel Mikhalchuk
 */
@Configuration
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RequestBenchLogger requestBenchLogger() { return new RequestBenchLogger(); }

    @Bean
    public UserResource userResource() {
        return new UserResource();
    }

    @Bean
    public CompanyResource companyResource() {
        return new CompanyResource();
    }

}