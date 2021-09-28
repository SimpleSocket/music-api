package net.unknown.musicapi.itunes;

import java.util.Optional;

public interface ItunesApi {
    Optional<String> searchArtist(String keyword);
    Optional<String> getTopArtistAlbums(String artistId);
}
