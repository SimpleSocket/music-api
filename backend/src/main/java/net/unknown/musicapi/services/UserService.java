package net.unknown.musicapi.services;

import net.unknown.musicapi.dtos.ArtistDto;
import net.unknown.musicapi.persistence.models.Artist;
import net.unknown.musicapi.persistence.repositories.ArtistRepo;
import net.unknown.musicapi.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ArtistRepo artistRepo;

    @Autowired
    public UserService(UserRepo userRepo, ArtistRepo artistRepo) {
        this.userRepo = userRepo;
        this.artistRepo = artistRepo;
    }

    public void saveArtistForUser(ArtistDto artistDto, long userId) {
        userRepo.findById(userId)
                .ifPresent(user -> {
                    Artist artist = new Artist(artistDto);
                    artist.addUser(user);
                    artistRepo.save(artist);
                    user.addArtist(artist);
                    userRepo.save(user);
                });
    }

    public boolean isFavoriteArtist(long amgArtistId, long userId) {
        return userRepo
                .findById(userId)
                .map(user -> user.isArtistPresent(amgArtistId)).orElse(false);
    }
}
