package net.unknown.musicapi.security;


import net.unknown.musicapi.persistence.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserIdFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(UserIdFilter.class);

    private UserRepo userRepo;

    @Autowired
    public UserIdFilter(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = httpRequest.getHeader("Authorization");

        long authHeader;
        try {
            authHeader = Long.parseLong(header);
        } catch (NumberFormatException e) {
            log.info("Authorization header is not expected format, expected long, received {}", header);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        if (!userRepo.existsById(authHeader)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.info("Authorization header contained correct format id, but no user exists with that id {}", header);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
