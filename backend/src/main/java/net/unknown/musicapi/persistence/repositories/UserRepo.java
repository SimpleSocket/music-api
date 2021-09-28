package net.unknown.musicapi.persistence.repositories;

import net.unknown.musicapi.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
