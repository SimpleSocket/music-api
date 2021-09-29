package net.unknown.musicapi.persistence.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name = "user")
public class User {

    @Id
    private long id;

    @ManyToMany(mappedBy = "users")
    private List<Artist> artists = new ArrayList<>();

    public User(long id) {
        this.id = id;
    }

    public User() {
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
