package org.siri_hate.chat_service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is a configuration class for Spring MVC.
 * It sets up Cross-Origin Resource Sharing (CORS) mappings for the application.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * This method is used to add CORS mappings.
     * It allows requests from any origin and allows all HTTP methods.
     *
     * @param registry the CorsRegistry to add mappings to
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

}