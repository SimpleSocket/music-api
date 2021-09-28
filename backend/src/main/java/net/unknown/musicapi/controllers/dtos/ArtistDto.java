package net.unknown.musicapi.controllers.dtos;

import javax.persistence.Id;

public class ArtistDto {
    private long artistId;
    private String artistName;
    private long amgArtistId;

    public ArtistDto(long artistId, String artistName, long amgArtistId) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.amgArtistId = amgArtistId;
    }

    public ArtistDto() {
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public long getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(long amgArtistId) {
        this.amgArtistId = amgArtistId;
    }
}
