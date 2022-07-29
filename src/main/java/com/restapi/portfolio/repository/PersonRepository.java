package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author "Fausto Stradiotto"
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person> findByEmail(String email);
    boolean existsByEmail(String email);
}
