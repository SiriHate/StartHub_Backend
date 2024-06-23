package org.siri_hate.notification_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;

/**
 * This class is responsible for configuring Thymeleaf templates for the application.
 * It defines beans for the SpringTemplateEngine and the SpringResourceTemplateResolver.
 */
@Configuration
public class ThymeleafTemplateConfig {

    /**
     * This method creates a SpringTemplateEngine bean.
     * The SpringTemplateEngine is configured with a template resolver for HTML templates.
     *
     * @return a configured SpringTemplateEngine
     */
    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    /**
     * This method creates a SpringResourceTemplateResolver bean.
     * The SpringResourceTemplateResolver is configured to resolve HTML templates from the classpath.
     *
     * @return a configured SpringResourceTemplateResolver
     */
    @Bean
    public  SpringResourceTemplateResolver htmlTemplateResolver(){
        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
        emailTemplateResolver.setPrefix("classpath:/templates/mail-templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }

}