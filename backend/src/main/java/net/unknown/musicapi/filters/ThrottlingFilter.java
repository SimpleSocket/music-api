package net.unknown.musicapi.filters;

import io.github.bucket4j.Bucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ThrottlingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ThrottlingFilter.class);

    private final Bucket bucket;

    @Autowired
    public ThrottlingFilter(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        log.warn("User requests exceeded available API call count, service partially unavailable");

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setContentType("text/plain");
        httpResponse.setStatus(429);
        httpResponse.getWriter().append("Too many requests");
    }
}
