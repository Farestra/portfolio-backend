
package com.restapi.portfolio.service;

import com.restapi.portfolio.interfaces.ISchoolService;
import com.restapi.portfolio.models.School;
import com.restapi.portfolio.repository.SchoolRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author "Fausto Stradiotto"
 */
@Service
@Transactional
public class SchoolService implements ISchoolService{

    @Autowired
    private SchoolRepository schoolRepository;
            
    
    @Override
    public List<School> getSchools() {
        List<School> schoolsList = schoolRepository.findAll();
        return schoolsList;
    }

    @Override
    public void saveSchool(School school) {
        schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public School findSchool(Long id) {
        School school = schoolRepository.findById(id).orElse(null);
        return school;
    }

}
