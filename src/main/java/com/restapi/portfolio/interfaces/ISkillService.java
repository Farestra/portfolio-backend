package com.restapi.portfolio.interfaces;

import com.restapi.portfolio.models.Skill;
import java.util.List;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface ISkillService {
    public List<Skill> getSkills();
    
    public void saveSkill(Skill skill);
    
    public void deleteSkill(Long id);
    
    public Skill findSkill(Long id);
}
