package net.unknown.musicapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.provider.api")
public class ItunesApiConfiguration {

    private String hostname;
    private String artist;
    private String album;

    public ItunesApiConfiguration(String hostname, String artist, String album) {
        this.hostname = hostname;
        this.artist = artist;
        this.album = album;
    }

    public ItunesApiConfiguration() {

    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUriToSearchArtist(String keyword) {
        String hostname = this.hostname;
        String search = this.artist;

        return hostname.concat(search).concat(keyword);
    }

    public String getTopArtistAlbums(String artistId) {
        String hostname = this.hostname;
        String search = this.album;

        return hostname.concat(search).concat(artistId);
    }
}
