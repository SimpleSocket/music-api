package net.unknown.musicapi.providers;

import net.unknown.musicapi.configuration.ItunesApiConfiguration;
import net.unknown.musicapi.exceptions.ApiRequestFailed;
import net.unknown.musicapi.exceptions.ServiceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Component
public class ItunesClient {

    private static final Logger logger = LoggerFactory.getLogger(ItunesClient.class);

    private final HttpClient client;
    private final ItunesApiConfiguration itunesApiUris;

    @Autowired
    public ItunesClient(HttpClient client, ItunesApiConfiguration itunesApiUris) {
        this.client = client;
        this.itunesApiUris = itunesApiUris;
    }

    private String performGet(String uri) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Request to the following url {} failed, received the following exception", request.uri(), e);
            throw new ApiRequestFailed("");
        }

        if (response.statusCode() == SERVICE_UNAVAILABLE.value()){
            throw new ServiceUnavailableException("Service is unavailable at the current time");
        }


        return response.body();
    }

    @Cacheable("artists")
    public Optional<String> searchArtist(String keyword) {
        String searchUri = itunesApiUris.getUriToSearchArtist(keyword);

        return Optional.of(performGet(searchUri));
    }

    @Cacheable("topAlbums")
    public Optional<String> getTopArtistAlbums(String artistId) {
        String topAlbumsUri = itunesApiUris.getTopArtistAlbums(artistId);

        return Optional.of(performGet(topAlbumsUri));
    }
}
