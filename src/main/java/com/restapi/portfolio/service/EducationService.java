
package com.restapi.portfolio.service;

import com.restapi.portfolio.interfaces.IEducationService;
import com.restapi.portfolio.models.Education;
import com.restapi.portfolio.repository.EducationRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author "Fausto Stradiotto"
 */
@Service
@Transactional
public class EducationService implements IEducationService{

    @Autowired
    private EducationRepository educationRepository;
    
    @Override
    public List<Education> getEducations() {
        return educationRepository.findAll();
    }

    @Override
    public void saveEducation(Education education) {
        educationRepository.save(education);
    }

    @Override
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }

    @Override
    public Education findEducation(Long id) {
        Education education = educationRepository.findById(id).orElse(null);
        return education;
    }

}
