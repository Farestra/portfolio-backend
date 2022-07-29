package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author "Fausto Stradiotto"
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long>{
    
}
