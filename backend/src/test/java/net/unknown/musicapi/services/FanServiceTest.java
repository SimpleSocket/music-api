package net.unknown.musicapi.services;

import net.unknown.musicapi.dtos.ArtistDto;
import net.unknown.musicapi.persistence.models.Artist;
import net.unknown.musicapi.persistence.models.Fan;
import net.unknown.musicapi.persistence.repositories.ArtistRepo;
import net.unknown.musicapi.persistence.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FanServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ArtistRepo artistRepo;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveArtistForUser_UserExists() {
        long userId = 1L;
        ArtistDto artistDto = new ArtistDto(1, "ab", 1); // Create a valid ArtistDto here
        Fan fan = mock(Fan.class);
        when(userRepo.findById(userId)).thenReturn(Optional.of(fan));

        userService.saveArtistForUser(artistDto, userId);

        verify(userRepo).findById(userId);
        verify(artistRepo).save(any(Artist.class));
        verify(fan).addArtist(any(Artist.class)); // Assuming User has an addArtist method
    }

    @Test
    public void testSaveArtistForUser_UserDoesNotExist() {
        long userId = 1L;
        ArtistDto artistDto = new ArtistDto(1, "ab", 1); // Create a valid ArtistDto here
        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        userService.saveArtistForUser(artistDto, userId);

        verify(userRepo).findById(userId);
        verify(artistRepo, never()).save(any(Artist.class));
    }

    @Test
    public void testIsFavoriteArtist_ArtistIsFavorite() {
        long userId = 1L;
        long amgArtistId = 100L;
        Fan fan = mock(Fan.class);
        when(userRepo.findById(userId)).thenReturn(Optional.of(fan));
        when(fan.isArtistPresent(amgArtistId)).thenReturn(true);

        boolean result = userService.isFavoriteArtist(amgArtistId, userId);

        assertTrue(result);
        verify(userRepo).findById(userId);
        verify(fan).isArtistPresent(amgArtistId);
    }

    @Test
    public void testIsFavoriteArtist_ArtistIsNotFavorite() {
        long userId = 1L;
        long amgArtistId = 100L;
        Fan fan = mock(Fan.class);
        when(userRepo.findById(userId)).thenReturn(Optional.of(fan));
        when(fan.isArtistPresent(amgArtistId)).thenReturn(false);

        boolean result = userService.isFavoriteArtist(amgArtistId, userId);

        assertFalse(result);
        verify(userRepo).findById(userId);
        verify(fan).isArtistPresent(amgArtistId);
    }

    @Test
    public void testIsFavoriteArtist_UserDoesNotExist() {
        long userId = 1L;
        long amgArtistId = 100L;
        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        boolean result = userService.isFavoriteArtist(amgArtistId, userId);

        assertFalse(result);
        verify(userRepo).findById(userId);
    }

}
