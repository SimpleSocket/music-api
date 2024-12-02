package net.unknown.musicapi.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.unknown.musicapi.persistence.repositories.FanRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserIdFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(UserIdFilter.class);

    private final FanRepo fanRepo;

    @Autowired
    public UserIdFilter(FanRepo fanRepo) {
        this.fanRepo = fanRepo;
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
        if (!fanRepo.existsById(authHeader)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.info("Authorization header contained correct format id, but no user exists with that id {}", header);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
