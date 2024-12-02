package net.unknown.musicapi.configuration;

import net.unknown.musicapi.filters.UserIdFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

    @Bean("secure")
    public FilterRegistrationBean<UserIdFilter> securityFilter(
            @Value("${security.url.pattern}") String urlPattern, UserIdFilter userIdFilter) {
        FilterRegistrationBean<UserIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(userIdFilter);
        registrationBean.addUrlPatterns(urlPattern);
        return registrationBean;
    }
}
