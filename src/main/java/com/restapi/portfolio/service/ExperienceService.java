
package com.restapi.portfolio.service;

import com.restapi.portfolio.interfaces.IExperienceService;
import com.restapi.portfolio.models.Experience;
import com.restapi.portfolio.repository.ExperienceRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author "Fausto Stradiotto"
 */
@Service
@Transactional
public class ExperienceService implements IExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;
    
    @Override
    public List<Experience> getExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public void saveExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    @Override
    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public Experience findExperience(Long id) {
        Experience experience =  experienceRepository.findById(id).orElse(null);
        return experience;
    }

}
