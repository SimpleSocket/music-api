package net.unknown.musicapi.controllers;


import net.unknown.musicapi.dtos.ArtistDto;
import net.unknown.musicapi.providers.ItunesClient;
import net.unknown.musicapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private static final Logger log = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ItunesClient itunesClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchArtist(@RequestParam(name = "keyword") String keyword) {
        Optional<String> artists = itunesClient.searchArtist(keyword);
        return artists.map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveArtist(@RequestBody ArtistDto artistDto, @RequestHeader("Authorization") long id) {

        userService.saveArtistForUser(artistDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{amgArtistId}/top-albums", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTopArtistAlbums(@PathVariable("amgArtistId") long amgArtistId, @RequestHeader("Authorization") long id) {

        if (userService.isFavoriteArtist(amgArtistId, id)) {
            String amgArtistIdStr = String.valueOf(amgArtistId);
            return itunesClient.getTopArtistAlbums(amgArtistIdStr).map(ResponseEntity::ok).orElse(null);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Artists top albums can only be viewed when selected artist is saved as favorite");
    }
}
