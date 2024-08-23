package net.unknown.musicapi.persistence.repositories;

import net.unknown.musicapi.persistence.models.Fan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FanRepo extends JpaRepository<Fan, Long> {
}
