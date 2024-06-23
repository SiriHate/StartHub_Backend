package org.siri_hate.chat_service.configuration;

import org.siri_hate.chat_service.security.JWTService;
import org.siri_hate.chat_service.security.JwtChannelInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * This class is a configuration class for WebSocket messaging in Spring.
 * It sets up the WebSocket endpoints, message broker, CORS filter, and channel interceptors.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private final JwtChannelInterceptor jwtChannelInterceptor;

    private final JWTService jwtService;


    /**
     * Constructor for the WebSocketConfiguration class.
     * It initializes the JWTService and JwtChannelInterceptor.
     *
     * @param jwtService the JWT service
     * @param jwtChannelInterceptor the JWT channel interceptor
     */
    @Autowired
    public WebSocketConfiguration(JWTService jwtService, JwtChannelInterceptor jwtChannelInterceptor) {
        this.jwtService = jwtService;
        this.jwtChannelInterceptor = jwtChannelInterceptor;
    }

    /**
     * This method configures the message broker.
     * It enables a simple broker and sets the application destination prefixes.
     *
     * @param config the MessageBrokerRegistry to configure
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * This method registers the STOMP endpoints.
     * It adds an endpoint, sets the allowed origin patterns, enables SockJS, and sets the handshake interceptors.
     *
     * @param registry the StompEndpointRegistry to add endpoints to
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS()
                .setInterceptors(new JwtHandshakeInterceptor(jwtService));
    }

    /**
     * This method provides a CORS filter bean.
     * The CORS filter is used to handle Cross-Origin Resource Sharing.
     *
     * @return a new CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * This method configures the client inbound channel.
     * It adds the JWT channel interceptor to the channel registration.
     *
     * @param registration the ChannelRegistration to configure
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(jwtChannelInterceptor);
    }

}