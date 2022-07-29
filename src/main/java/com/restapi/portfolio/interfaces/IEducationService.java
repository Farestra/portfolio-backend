package com.restapi.portfolio.interfaces;


import com.restapi.portfolio.models.Education;
import java.util.List;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface IEducationService {
    public List<Education> getEducations();
    
    public void saveEducation(Education education);
    
    public void deleteEducation(Long id);
    
    public Education findEducation(Long id);
}
