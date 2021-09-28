package net.unknown.musicapi.itunes;

import net.unknown.musicapi.exceptions.ApiRequestFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Component
public class HttpClientItunesApi implements ItunesApi {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientItunesApi.class);

    private final HttpClient client;
    private final ItunesApiUris itunesApiUris;

    @Autowired
    public HttpClientItunesApi(HttpClient client, ItunesApiUris itunesApiUris){
        this.client = client;
        this.itunesApiUris = itunesApiUris;
    }

    private String performGet(String url){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Request to the following url {} failed, received the following exception", url, e);
            throw new ApiRequestFailed("");
        }
        return response.body();
    }

    @Override
    public Optional<String> searchArtist(String keyword) {
        String buildUrl = itunesApiUris.getUriToSearchArtist(keyword);
        return Optional.of(performGet(buildUrl));
    }

    @Override
    public Optional<String> getTopArtistAlbums(String artistId) {
        String buildUrl = itunesApiUris.getTopArtistAlbums(artistId);
        return Optional.of(performGet(buildUrl));
    }
}
