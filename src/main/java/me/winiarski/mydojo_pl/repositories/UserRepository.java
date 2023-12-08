package me.winiarski.mydojo_pl.repositories;

import me.winiarski.mydojo_pl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

}
