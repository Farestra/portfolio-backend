package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.ERole;
import com.restapi.portfolio.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
}
