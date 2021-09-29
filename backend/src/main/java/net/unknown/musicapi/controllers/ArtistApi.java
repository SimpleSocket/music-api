package net.unknown.musicapi.controllers;


import net.unknown.musicapi.controllers.dtos.ArtistDto;
import net.unknown.musicapi.providers.itunes.CacheableItunesApi;
import net.unknown.musicapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistApi {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheableItunesApi cacheableItunesApi;

    @GetMapping(value = "/search/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchArtist(@PathVariable ("keyword") String keyword) {
        Optional<String> artists = cacheableItunesApi.searchArtist(keyword);
        return artists.map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveArtist(@RequestBody ArtistDto artistDto, @RequestHeader("Authorization") long id) {
        userService.saveArtistForUser(artistDto, id);

        if (userService.isFavoriteArtist(artistDto.getAmgArtistId(), id)) {
           return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/top/{amgArtistId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTopArtistAlbums(@PathVariable ("amgArtistId") long amgArtistId, @RequestHeader("Authorization") long id) {

        if (userService.isFavoriteArtist(amgArtistId, id)) {
            String amgArtistIdStr = String.valueOf(amgArtistId);
            return cacheableItunesApi.getTopArtistAlbums(amgArtistIdStr).map(ResponseEntity::ok).orElse(null);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
