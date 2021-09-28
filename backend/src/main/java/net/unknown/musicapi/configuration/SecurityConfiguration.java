package net.unknown.musicapi.configuration;

import net.unknown.musicapi.security.UserIdFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

    @Value("${security.url.pattern}")
    private String urlPattern;

    @Bean
    public FilterRegistrationBean<UserIdFilter> securityFilter() {
        FilterRegistrationBean<UserIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserIdFilter());
        registrationBean.addUrlPatterns(urlPattern);
        return registrationBean;
    }
}
