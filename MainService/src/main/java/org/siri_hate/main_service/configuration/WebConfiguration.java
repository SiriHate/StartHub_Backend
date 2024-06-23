package org.siri_hate.main_service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is responsible for the web configuration of the application.
 * It implements the WebMvcConfigurer interface to provide CORS mappings.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * This method is used to add CORS mappings.
     * It allows all origins and methods for all paths.
     *
     * @param registry the CorsRegistry object
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

}