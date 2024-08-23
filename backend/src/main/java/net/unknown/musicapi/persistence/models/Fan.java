package net.unknown.musicapi.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/*
The entity in the task is user but this is a reserved keyword on Db2 database which causes a lot of issues, which
were not present a few years ago. For that reason our user is a fan from now on.
 */
@Entity(name = "fan")
@Table(name = "fan")
public class Fan {

    @Id
    private long id;

    @ManyToMany(mappedBy = "fans")
    private List<Artist> artists = new ArrayList<>();

    public Fan(long id) {
        this.id = id;
    }

    public Fan() {
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public boolean isArtistPresent(long amgArtistId) {
        return artists.stream().anyMatch(artist -> artist.getAmgArtistId() == amgArtistId);
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
