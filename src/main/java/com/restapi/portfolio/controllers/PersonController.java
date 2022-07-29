
package com.restapi.portfolio.controllers;

import com.restapi.portfolio.exception.ResourceNotFoundException;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author "Fausto Stradiotto"
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    //obtenemos una persona por el email
    @GetMapping("/person/{email}")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Person> getTutorialById(@PathVariable("email") String email) {
      Person person = personRepository.findByEmail(email)
              .orElseThrow(() -> new ResourceNotFoundException("no existe persona con email = " + email));

      return new ResponseEntity<>(person, HttpStatus.OK);
    }
    
    //creamos una persona nueva por el método post
    @PostMapping("/person")
    @PreAuthorize("asRole('ROLE_ADMIN')")
    public ResponseEntity<Person> createTutorial(@RequestBody Person person) {
      Person _person = personRepository.save
        (new Person(
              person.getName(),
      person.getBackImg(),
              person.getProfileimg(),
              person.getEmail()
      ,person.getPassword(), 
              person.getLocation(), 
              person.getAbout()));
      return new ResponseEntity<>(_person, HttpStatus.CREATED);
    }

    @PutMapping("/person/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Person> updateTutorial(@PathVariable("id") long id, @RequestBody Person person) {
      Person _person = personRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No hay persona con el id = " + id));

      _person.setName(person.getName());
      _person.setBackImg(person.getBackImg());
      _person.setProfileimg(person.getProfileimg());
      _person.setEmail(person.getEmail());
      _person.setPassword(person.getPassword());
      _person.setLocation(person.getLocation());
      _person.setAbout(person.getAbout());

      return new ResponseEntity<>(personRepository.save(_person), HttpStatus.OK);
    }
    
    @DeleteMapping("/person/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
      personRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
