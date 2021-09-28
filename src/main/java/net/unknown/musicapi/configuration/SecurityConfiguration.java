package net.unknown.musicapi.configuration;

import net.unknown.musicapi.security.UserIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SecurityConfiguration {

//    @Bean
    public FilterRegistrationBean<UserIdFilter> securityFilter() {
        FilterRegistrationBean<UserIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserIdFilter());
        registrationBean.addUrlPatterns("/album/*");
        return registrationBean;
    }
}
