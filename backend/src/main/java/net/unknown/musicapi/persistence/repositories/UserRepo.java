package net.unknown.musicapi.persistence.repositories;

import net.unknown.musicapi.persistence.models.Fan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Fan, Long> {
}
