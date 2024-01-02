package pl.mydojo.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mydojo.app.entities.Role;
import pl.mydojo.app.entities.RoleType;
import pl.mydojo.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    User findUserById(Long id);

    @Query("SELECT r FROM users u JOIN u.roles r WHERE u.id = :userId")
    List<Role> findRolesByUserId(@Param("userId") Long userId);

    @Query("SELECT u FROM users u JOIN u.roles r WHERE r.type = :roleType")
    List<User> findAllByRole(@Param("roleType") RoleType roleType);

    @Query("SELECT COUNT(u) FROM users u JOIN u.roles r WHERE r.type = :roleType")
    long countByRole(@Param("roleType") RoleType roleType);

    @Query("SELECT COUNT(u) FROM users u WHERE u.enabled = :status")
    long countByEnabled(@Param("status") boolean status);

}
