package net.unknown.musicapi.configuration;

import io.github.bucket4j.Bucket;
import net.unknown.musicapi.filters.ThrottlingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ThrottlingConfiguration {

    @Bean
    public Bucket createNewBucket(@Value("${throttling.hourly.rate}") int tokenNumber ) {
        return Bucket.builder()
                .addLimit(limit -> limit
                        .capacity(tokenNumber)
                        .refillGreedy(tokenNumber, Duration.ofHours(1)))
                .build();
    }

    @Bean("throttle")
    public FilterRegistrationBean<ThrottlingFilter> throttlingFilter(
            @Value("${throttling.url.pattern}") String urlPattern, ThrottlingFilter throttlingFilter) {
        FilterRegistrationBean<ThrottlingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(throttlingFilter);
        registrationBean.addUrlPatterns(urlPattern);
        return registrationBean;
    }
}
