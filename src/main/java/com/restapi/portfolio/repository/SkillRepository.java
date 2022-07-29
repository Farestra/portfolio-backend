package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author "Fausto Stradiotto"
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
    
}
