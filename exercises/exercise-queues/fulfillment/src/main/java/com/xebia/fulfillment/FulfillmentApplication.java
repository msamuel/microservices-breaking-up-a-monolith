package com.xebia.fulfillment;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger
public class FulfillmentApplication {

    private static Logger LOG = LoggerFactory.getLogger(FulfillmentApplication.class);

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
            //Root level documentation
            .apiInfo(new ApiInfo(
                "Fulfillment API",
                "This page provides details of the REST API for the Fulfillment service",
                "Go and explore ...",
                null,
                null,
                null
            ))
            .useDefaultResponseMessages(false)
                //Map the specific URL patterns into Swagger
            .includePatterns("/.*");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(FulfillmentApplication.class, args);
        FulfillmentApplication application = applicationContext.getBean(FulfillmentApplication.class);
    }

    @Bean
    public FilterRegistrationBean commonsRequestLoggingFilter()
    {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CommonsRequestLoggingFilter());
        return registrationBean;
    }

}

