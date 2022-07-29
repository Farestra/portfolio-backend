package com.restapi.portfolio.interfaces;

import com.restapi.portfolio.models.Achievement;
import java.util.List;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface IAchievementService {
    public List<Achievement> getAchievements();
    
    public void saveAchievement(Achievement achievement);
    
    public void deleteAchievement(Long id);
    
    public Achievement findAchievement(Long id);
}
