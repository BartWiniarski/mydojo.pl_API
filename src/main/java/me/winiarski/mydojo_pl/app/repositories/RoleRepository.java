package me.winiarski.mydojo_pl.app.repositories;

import me.winiarski.mydojo_pl.app.entities.Role;
import me.winiarski.mydojo_pl.app.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByType(RoleType type);
}
