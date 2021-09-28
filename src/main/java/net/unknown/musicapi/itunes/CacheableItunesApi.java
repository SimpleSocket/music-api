package net.unknown.musicapi.itunes;

import net.unknown.musicapi.itunes.HttpClientItunesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CacheableItunesApi implements ItunesApi {

    private HttpClientItunesApi httpClientItunesApi;

    @Autowired
    public CacheableItunesApi(HttpClientItunesApi httpClientItunesApi) {
        this.httpClientItunesApi = httpClientItunesApi;
    }

    @Override
    @Cacheable("artists")
    public Optional<String> searchArtist(String keyword) {
        return httpClientItunesApi.searchArtist(keyword);
    }

    @Override
    @Cacheable("topAlbums")
    public Optional<String> getTopArtistAlbums(String artistId) {
        return httpClientItunesApi.getTopArtistAlbums(artistId);
    }
}
