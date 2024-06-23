package org.siri_hate.chat_service.configuration;

import org.siri_hate.chat_service.security.JWTService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * This class is a custom implementation of the HandshakeInterceptor interface.
 * It is used to intercept WebSocket handshake requests.
 * The methods in this class are called before and after the handshake between the client and server.
 */
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    // Instance of the JWTService.
    private JWTService jwtService;

    /**
     * Constructor for the JwtHandshakeInterceptor class.
     * It initializes the JWTService.
     *
     * @param jwtService the JWT service
     */
    public JwtHandshakeInterceptor(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * This method is called before the handshake between the client and server.
     * It can be used to add attributes to the WebSocket session or to prevent the handshake.
     *
     * @param request the current HTTP request
     * @param response the current HTTP response
     * @param wsHandler the target WebSocket handler
     * @param attributes attributes from the HTTP handshake to associate with the WebSocket session
     * @return whether to proceed with the handshake (true) or abort (false)
     * @throws Exception in case of any errors
     */
    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
                                  ) throws Exception {
        return true;
    }

    /**
     * This method is called after the handshake has been done.
     * It can be used to perform any necessary cleanup or additional processing.
     *
     * @param request the current HTTP request
     * @param response the current HTTP response
     * @param wsHandler the target WebSocket handler
     * @param exception any exception that occurred during the handshake, or null if none
     */
    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception
                              ) {
    }

}