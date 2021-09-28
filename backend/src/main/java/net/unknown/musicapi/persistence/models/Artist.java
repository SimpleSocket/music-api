package net.unknown.musicapi.persistence.models;

import javax.persistence.*;
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
    private List<User> users = new ArrayList<>();

    public Artist() {
    }

    public Artist(long artistId, String artistName, long amgArtistId) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.amgArtistId = amgArtistId;
    }

    public void addUser(User user){
        users.add(user);
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
