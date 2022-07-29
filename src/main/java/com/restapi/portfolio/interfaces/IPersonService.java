package com.restapi.portfolio.interfaces;

import com.restapi.portfolio.models.Person;
import java.util.List;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface IPersonService {
    public List<Person> getPersons();
    
    public void savePerson(Person person);
    
    public void deletePerson(Long id);
    
    public Person findPerson(Long id);
}
