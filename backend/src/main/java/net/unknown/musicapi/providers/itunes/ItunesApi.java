package net.unknown.musicapi.providers.itunes;

import java.util.Optional;

public interface ItunesApi {
    Optional<String> searchArtist(String keyword);
    Optional<String> getTopArtistAlbums(String artistId);
}
