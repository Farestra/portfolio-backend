package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author "Fausto Stradiotto"
 */
@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{
    
}
