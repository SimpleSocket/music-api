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

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
