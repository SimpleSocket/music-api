package net.unknown.musicapi.persistence.repositories;

import net.unknown.musicapi.persistence.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepo extends JpaRepository<Artist, Long> {
}
