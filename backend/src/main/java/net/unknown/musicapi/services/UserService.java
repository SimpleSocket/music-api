package net.unknown.musicapi.services;

import net.unknown.musicapi.dtos.ArtistDto;
import net.unknown.musicapi.persistence.models.Artist;
import net.unknown.musicapi.persistence.repositories.ArtistRepo;
import net.unknown.musicapi.persistence.repositories.FanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final FanRepo fanRepo;
    private final ArtistRepo artistRepo;

    @Autowired
    public UserService(FanRepo fanRepo, ArtistRepo artistRepo) {
        this.fanRepo = fanRepo;
        this.artistRepo = artistRepo;
    }

    public void saveArtistForUser(ArtistDto artistDto, long userId) {
        fanRepo.findById(userId)
                .ifPresent(fan -> {
                    Artist artist = new Artist(artistDto);

                    artist.addUser(fan);
                    artistRepo.save(artist);

                    fan.addArtist(artist);
                    fanRepo.save(fan);
                });
    }

    public boolean isFavoriteArtist(long amgArtistId, long userId) {
        return fanRepo
                .findById(userId)
                .map(user -> user.isArtistPresent(amgArtistId)).orElse(false);
    }
}
