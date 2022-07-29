
package com.restapi.portfolio.service;

import com.restapi.portfolio.interfaces.IAchievementService;
import com.restapi.portfolio.models.Achievement;
import com.restapi.portfolio.repository.AchievementRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author "Fausto Stradiotto"
 */
@Service
@Transactional
public class AchievementService implements IAchievementService{
    @Autowired
    private AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    public void saveAchievement(Achievement achievement) {
        achievementRepository.save(achievement);
    }

    @Override
    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    @Override
    public Achievement findAchievement(Long id) {
        Achievement achievement = achievementRepository.findById(id).orElse(null);
        return achievement;
    }
}
