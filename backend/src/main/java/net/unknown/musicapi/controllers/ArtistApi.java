package net.unknown.musicapi.controllers;

import net.unknown.musicapi.controllers.dtos.AmgArtistId;
import net.unknown.musicapi.controllers.dtos.ArtistDto;
import net.unknown.musicapi.controllers.dtos.Keyword;
import net.unknown.musicapi.providers.itunes.CacheableItunesApi;
import net.unknown.musicapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistApi {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheableItunesApi cacheableItunesApi;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchArtist(@RequestBody Keyword keyword) {
        Optional<String> artists = cacheableItunesApi.searchArtist(keyword.getKeyword());
        return artists.map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveArtist(@RequestBody ArtistDto artistDto, @RequestHeader("Authorization") long id) {
        userService.saveArtistForUser(artistDto, id);

        if (userService.isFavoriteArtist(artistDto.getAmgArtistId(), id)) {
            ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/top", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTopArtistAlbums(@RequestBody AmgArtistId amgArtistId, @RequestHeader("Authorization") long id) {

        if (userService.isFavoriteArtist(amgArtistId.getAmgArtistId(), id)) {
            String amgArtistIdStr = String.valueOf(amgArtistId.getAmgArtistId());
            return cacheableItunesApi.getTopArtistAlbums(amgArtistIdStr).map(ResponseEntity::ok).orElse(null);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
