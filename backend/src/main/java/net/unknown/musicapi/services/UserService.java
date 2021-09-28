package net.unknown.musicapi.services;

import net.unknown.musicapi.controllers.dtos.ArtistDto;
import net.unknown.musicapi.persistence.models.Artist;
import net.unknown.musicapi.persistence.models.User;
import net.unknown.musicapi.persistence.repositories.ArtistRepo;
import net.unknown.musicapi.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;
    private ArtistRepo artistRepo;

    @Autowired
    public UserService(UserRepo userRepo, ArtistRepo artistRepo) {
        this.userRepo = userRepo;
        this.artistRepo = artistRepo;
    }

    public void saveArtistForUser(ArtistDto artistDto, long userId) {
        Optional<User> wrappedUser = userRepo.findById(userId);

        if (wrappedUser.isPresent()) {
            User user = wrappedUser.get();
            Artist artist = new Artist(artistDto.getArtistId(), artistDto.getArtistName(), artistDto.getAmgArtistId());
            artist.addUser(user);
            artistRepo.save(artist);
        }
    }

    public boolean isFavoriteArtist(long amgArtistId, long userId) {
        Optional<User> wrappedUser = userRepo.findById(userId);
        return wrappedUser.map(user -> user.isArtistPresent(amgArtistId)).orElse(false);
    }
}
