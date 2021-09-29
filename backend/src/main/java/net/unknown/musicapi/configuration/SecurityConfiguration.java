package net.unknown.musicapi.configuration;

import net.unknown.musicapi.security.UserIdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {


    @Autowired
    private UserIdFilter userIdFilter;

    @Bean
    public FilterRegistrationBean<UserIdFilter> securityFilter( @Value("${security.url.pattern}") String urlPattern) {
        FilterRegistrationBean<UserIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(userIdFilter);
        registrationBean.addUrlPatterns(urlPattern);
        return registrationBean;
    }
}
