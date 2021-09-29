package net.unknown.musicapi.providers.itunes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CacheableItunesApi implements ItunesApi {

    private static final Logger logger = LoggerFactory.getLogger(CacheableItunesApi.class);

    private HttpClientItunesApi httpClientItunesApi;

    @Autowired
    public CacheableItunesApi(HttpClientItunesApi httpClientItunesApi) {
        this.httpClientItunesApi = httpClientItunesApi;
    }

    @Override
    @Cacheable("artists")
    public Optional<String> searchArtist(String keyword) {
        logger.info("Starting population of artists cache");
        return httpClientItunesApi.searchArtist(keyword);
    }

    @Override
    @Cacheable("topAlbums")
    public Optional<String> getTopArtistAlbums(String artistId) {
        logger.info("Starting population of top albums cache");
        return httpClientItunesApi.getTopArtistAlbums(artistId);
    }
}
