package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author "Fausto Stradiotto"
 */
@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long>{
    boolean existsById(Long id);
}
