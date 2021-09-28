package net.unknown.musicapi.controllers.dtos;

public class TopAlbum {

    private long amgArtistId;

    public TopAlbum(long artistId) {
        this.amgArtistId = artistId;
    }

    public TopAlbum() {
    }

    public long getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(long amgArtistId) {
        this.amgArtistId = amgArtistId;
    }
}
