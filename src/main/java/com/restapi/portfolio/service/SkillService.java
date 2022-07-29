
package com.restapi.portfolio.service;

import com.restapi.portfolio.interfaces.ISkillService;
import com.restapi.portfolio.models.Skill;
import com.restapi.portfolio.repository.SkillRepository;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author "Fausto Stradiotto"
 */
@Service
@Transactional
public class SkillService implements ISkillService {

    @Autowired
    private SkillRepository skillRepository;
    
    @Override
    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }

    @Override
    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public Skill findSkill(Long id) {
        Skill skill = skillRepository.findById(id).orElse(null);
        return skill;
    }

}
