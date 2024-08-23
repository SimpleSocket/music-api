package net.unknown.musicapi.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import net.unknown.musicapi.dtos.ArtistDto;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "artist")
@Table(name = "artist")
public class Artist {

    @Id
    private long artistId;
    private String artistName;
    private long amgArtistId;

    @ManyToMany()
    private List<Fan> fans = new ArrayList<>();

    public Artist() {
    }

    public Artist(long artistId, String artistName, long amgArtistId) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.amgArtistId = amgArtistId;
    }

    public Artist(ArtistDto artistDto) {
        this.artistId = artistDto.artistId();
        this.artistName = artistDto.artistName();
        this.amgArtistId = artistDto.amgArtistId();
    }

    public void addUser(Fan fan) {
        fans.add(fan);
    }

    public long getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public long getAmgArtistId() {
        return amgArtistId;
    }
}
