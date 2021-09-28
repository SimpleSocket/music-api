package net.unknown.musicapi.providers.itunes;

import net.unknown.musicapi.configuration.ItunesApiConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItunesApiUris {

    private ItunesApiConfiguration itunesApiConfiguration;

    @Autowired
    public ItunesApiUris(ItunesApiConfiguration itunesApiConfiguration) {
        this.itunesApiConfiguration = itunesApiConfiguration;
    }

    public String getUriToSearchArtist(String keyword) {
        String hostname = itunesApiConfiguration.getHostname();
        String search = itunesApiConfiguration.getArtist();

        return hostname.concat(search).concat(keyword);
    }

    public String getTopArtistAlbums(String artistId) {
        String hostname = itunesApiConfiguration.getHostname();
        String search = itunesApiConfiguration.getAlbum();

        return hostname.concat(search).concat(artistId);
    }

}
