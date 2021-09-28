package net.unknown.musicapi.persistence.models;

import javax.persistence.*;
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

    public void addArtist(Artist artist){
        artists.add(artist);
    }

    public boolean isArtistPresent(long artistId){
        return artists.stream().anyMatch( artist -> artist.getArtistId() == artistId);
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
