package org.siri_hate.user_service.configuration;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Configuration class for configuring web-related settings.
 * Implements WebMvcConfigurer to customize MVC configuration.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Configures cross-origin resource sharing (CORS) for all endpoints.
     *
     * @param registry CORS registry to add mappings and configurations.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

}