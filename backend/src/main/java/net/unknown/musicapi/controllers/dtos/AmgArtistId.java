package net.unknown.musicapi.controllers.dtos;

public class AmgArtistId {

    private long amgArtistId;

    public AmgArtistId(long artistId) {
        this.amgArtistId = artistId;
    }

    public AmgArtistId() {
    }

    public long getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(long amgArtistId) {
        this.amgArtistId = amgArtistId;
    }
}
