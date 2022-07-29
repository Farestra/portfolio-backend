
package com.restapi.portfolio.service;

import com.restapi.portfolio.interfaces.IPersonService;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author "Fausto Stradiotto"
 */
@Service
@Transactional
public class PersonService implements IPersonService{
    
    @Autowired
    private PersonRepository personRepository;
    
    @Override
    public List<Person> getPersons() {
        List<Person> personsList = personRepository.findAll();
        return personsList;
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person findPerson(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        return person;
    }
    
    public Optional<Person> getByEmail(String email){
        return personRepository.findByEmail(email);
    }
    
}
