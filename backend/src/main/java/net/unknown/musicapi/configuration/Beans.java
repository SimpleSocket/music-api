package net.unknown.musicapi.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class Beans {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public Caffeine caffeineConfig(@Value("${music.provider.cache.expiration}") int expirationInMinutes) {
        return Caffeine.newBuilder().expireAfterWrite(expirationInMinutes, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

}
