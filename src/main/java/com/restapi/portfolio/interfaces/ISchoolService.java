package com.restapi.portfolio.interfaces;


import com.restapi.portfolio.models.School;
import java.util.List;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface ISchoolService {
    public List<School> getSchools();
    
    public void saveSchool(School school);
    
    public void deleteSchool(Long id);
    
    public School findSchool(Long id);
}
