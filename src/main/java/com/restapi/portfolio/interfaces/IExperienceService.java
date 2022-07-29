package com.restapi.portfolio.interfaces;

import com.restapi.portfolio.models.Experience;
import java.util.List;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface IExperienceService {
    public List<Experience> getExperiences();
    
    public void saveExperience(Experience experience);
    
    public void deleteExperience(Long id);
    
    public Experience findExperience(Long id);
}
