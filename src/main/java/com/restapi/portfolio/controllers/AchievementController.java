
package com.restapi.portfolio.controllers;

import com.restapi.portfolio.exception.ResourceNotFoundException;
import com.restapi.portfolio.models.Achievement;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.repository.AchievementRepository;
import com.restapi.portfolio.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
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
public class AchievementController {
    //importamos repositorio propio y de la entidad padre
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private PersonRepository personRepository;
    
    //obtenemos todas los logros para una id de persona mediante el método GET
    @GetMapping("/person/{personId}/achievement")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Achievement>> getAllAchievementsByPersonId(@PathVariable(value = "personId") Long personId) {    
        //obtenemos el objeto persona por la id
        Person person = personRepository.findById(personId)
                //si no encuentra la persona lanza una excepción
          .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado una Persona con el id = " + personId));
        //obtiene los logros en una lista
        List<Achievement> achievements = new ArrayList<>();
      achievements.addAll(person.getAchievements());
      //retorna los logros
      return new ResponseEntity<>(achievements, HttpStatus.OK);
    }
    
    //obtenemos un logro por su id mediante el método GET
    
    @GetMapping("/achievement/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable(value = "id") Long id) {
      Achievement achievement = achievementRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No hay educación con el id = " + id));
      return new ResponseEntity<>(achievement, HttpStatus.OK);
    }
    
    //crear un logro nuevo mediante el método POST
    @PostMapping("/person/{personId}/achievement")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Achievement> createAchievement(@PathVariable(value = "personId") Long personId,
        @RequestBody Achievement achievementRequest) {
      Achievement achievement = personRepository.findById(personId).map(person -> {
        person.getAchievements().add(achievementRequest);
        return achievementRepository.save(achievementRequest);
      }).orElseThrow(() -> new ResourceNotFoundException("No hay persona con id = " + personId));
      return new ResponseEntity<>(achievement, HttpStatus.CREATED);
    }
    
    //actualizar educación mediante el método PUT
    
    @PutMapping("/achievement/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable("id") Long id, @RequestBody Achievement achievementRequest) {
      Achievement achievement = achievementRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Logro con id " + id + "no encontrado"));
      achievement.setName(achievementRequest.getName());
      achievement.setDetails(achievementRequest.getDetails());
      achievement.setImage(achievementRequest.getImage());
      achievement.setUrl(achievementRequest.getUrl());

      
      
      //guardamos actualizado
      return new ResponseEntity<>(achievementRepository.save(achievement), HttpStatus.OK);
    }
    
    //eliminar una educación por su id mediante el método DELETE
    @DeleteMapping("/achievement/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteEducation(@PathVariable("id") Long id) {
      achievementRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
