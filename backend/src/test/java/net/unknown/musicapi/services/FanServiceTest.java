package net.unknown.musicapi.services;

import net.unknown.musicapi.dtos.ArtistDto;
import net.unknown.musicapi.persistence.models.Artist;
import net.unknown.musicapi.persistence.models.Fan;
import net.unknown.musicapi.persistence.repositories.ArtistRepo;
import net.unknown.musicapi.persistence.repositories.FanRepo;
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

    private static final long USER_ID = 1L;

    private static final long AMD_ARTIST_ID = 100L;

    @Mock
    private FanRepo fanRepo;

    @Mock
    private ArtistRepo artistRepo;

    @InjectMocks
    private UserService userService;

    private final ArtistDto artistDto = new ArtistDto(1, "ab", 1);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void artistIsSavedForExistingFan() {
        Fan fan = mock(Fan.class);
        when(fanRepo.findById(USER_ID)).thenReturn(Optional.of(fan));

        userService.saveArtistForUser(artistDto, USER_ID);

        verify(fanRepo).findById(USER_ID);
        verify(artistRepo).save(any(Artist.class));
        verify(fanRepo).save(any(Fan.class));
        verify(fan).addArtist(any(Artist.class));
    }

    @Test
    public void artistIsNotSavedForNonExistingFan() {
        when(fanRepo.findById(USER_ID)).thenReturn(Optional.empty());

        userService.saveArtistForUser(artistDto, USER_ID);

        verify(fanRepo).findById(USER_ID);
        verify(artistRepo, never()).save(any(Artist.class));
    }

    @Test
    public void savedAristCountsAsFavorite() {
        Fan fan = mock(Fan.class);
        when(fanRepo.findById(USER_ID)).thenReturn(Optional.of(fan));
        when(fan.isArtistPresent(AMD_ARTIST_ID)).thenReturn(true);

        boolean result = userService.isFavoriteArtist(AMD_ARTIST_ID, USER_ID);

        assertTrue(result);
        verify(fanRepo).findById(USER_ID);
        verify(fan).isArtistPresent(AMD_ARTIST_ID);
    }

    @Test
    public void whenArtistIsNotSavedCheckingIsFavoriteResultsInFalse() {
        Fan fan = mock(Fan.class);
        when(fanRepo.findById(USER_ID)).thenReturn(Optional.of(fan));
        when(fan.isArtistPresent(AMD_ARTIST_ID)).thenReturn(false);

        boolean result = userService.isFavoriteArtist(AMD_ARTIST_ID, USER_ID);

        assertFalse(result);
        verify(fanRepo).findById(USER_ID);
        verify(fan).isArtistPresent(AMD_ARTIST_ID);
    }
}
