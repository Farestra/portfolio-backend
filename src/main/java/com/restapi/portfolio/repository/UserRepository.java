package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
