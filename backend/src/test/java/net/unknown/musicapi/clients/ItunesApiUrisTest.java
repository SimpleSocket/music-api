package net.unknown.musicapi.clients;

import net.unknown.musicapi.configuration.ItunesApiConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//Raising the context for this trivial tests is not a good way, however the idea is to show the test
//best way would be to create the objects by hand
@SpringBootTest
@ActiveProfiles("test")
public class ItunesApiUrisTest {

    @Autowired
    private ItunesApiConfiguration itunesApiUris;

    @Test
    public void constructUriForTop5AlbumsRetrieval() {
        String artistId = "123";
        Assertions.assertThat(itunesApiUris.getTopArtistAlbums(artistId)).isEqualTo("https://localhost.com/lookup?entity=album&limit=5&amgArtistId=" + artistId);
    }

    @Test
    public void constructUriForSearchingArtistByKeyword() {
        String artistId = "123";
        Assertions.assertThat(itunesApiUris.getUriToSearchArtist(artistId)).isEqualTo("https://localhost.com/search?entity=allArtist&term=" + artistId);
    }

}
