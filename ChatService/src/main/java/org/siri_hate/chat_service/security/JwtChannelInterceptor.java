package org.siri_hate.chat_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.Principal;
import java.util.List;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {

    final private JWTService jwtService;

    @Autowired
    public JwtChannelInterceptor(final JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        List<String> authorization = null;

        if (accessor != null) {
            authorization = accessor.getNativeHeader("Authorization");
        }

        if (authorization != null && !authorization.isEmpty()) {
            try {
                String token = authorization.get(0).substring("Bearer ".length());
                if (!token.isEmpty()) {
                    Claims claims = jwtService.extractAllClaims(token);
                    String username = claims.getSubject();
                    Principal principal = () -> username;
                    accessor.setUser(principal);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return message;
    }

}
