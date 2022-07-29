package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author "Fausto Stradiotto"
 */
@Repository
public interface SchoolRepository extends JpaRepository<School, Long>{
    
}
