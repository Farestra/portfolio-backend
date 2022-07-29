
package com.restapi.portfolio.controllers;

import com.restapi.portfolio.exception.ResourceNotFoundException;
import com.restapi.portfolio.models.Experience;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.repository.ExperienceRepository;
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
public class ExperienceController {
    //importamos repositorio propio y de la entidad padre
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private PersonRepository personRepository;
    
    //obtenemos todas las experiencias para una id de persona mediante el método GET
    @GetMapping("/person/{personId}/experience")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Experience>> getAllExperiencesByPersonId(@PathVariable(value = "personId") Long personId) {    
        //obtenemos el objeto persona por la id
        Person person = personRepository.findById(personId)
                //si no encuentra la persona lanza una excepción
          .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado una Persona con el id = " + personId));
        //obtiene las habilidades en una lista
        List<Experience> experiences = new ArrayList<>();
      experiences.addAll(person.getExperiences());
      //retorna las escuelas
      return new ResponseEntity<>(experiences, HttpStatus.OK);
    }
    
    //obtenemos una experiencia por su id mediante el método GET
    @GetMapping("/experience/{id}")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Experience> getShoolsById(@PathVariable(value = "id") Long id) {
      Experience experience = experienceRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No hay escuela con el id = " + id));
      return new ResponseEntity<>(experience, HttpStatus.OK);
    }
    
    //crear una experiencia nueva mediante el método POST
    @PostMapping("/person/{personId}/experience")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Experience> createSkill(@PathVariable(value = "personId") Long personId,
        @RequestBody Experience experienceRequest) {
      Experience experience = personRepository.findById(personId).map(person -> {
        person.getExperiences().add(experienceRequest);
        return experienceRepository.save(experienceRequest);
      }).orElseThrow(() -> new ResourceNotFoundException("No hay persona con id = " + personId));
      return new ResponseEntity<>(experience, HttpStatus.CREATED);
    }
    
    //actualizar experiencia mediante el método PUT
    @PutMapping("/experience/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Experience> updateSchool(@PathVariable("id") Long id, @RequestBody Experience experienceRequest) {
      Experience experience = experienceRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Escuela con id " + id + "no encontrada"));
      //actualizamos la compañia en la experiencia
      experience.setCompany(experienceRequest.getCompany());
      //actualizamos la posición en la experiencia
      experience.setPosition(experienceRequest.getPosition());
      //actualizamos la imagen en la experiencia
      experience.setExperienceImg(experienceRequest.getExperienceImg());
      //actualizamos la fecha de inicio
      experience.setExperienceStart(experienceRequest.getExperienceStart());
      //actualizamos la fecha de finalizacipon
      experience.setExperienceEnd(experienceRequest.getExperienceEnd());
      //actualizamos el modo de la experiencia
      experience.setExperienceMode(experienceRequest.getExperienceMode());
      //actualizamos la duración de la experiencia
      experience.setTimeElapsed(experienceRequest.getTimeElapsed());
      
      //guardamos actualizado
      return new ResponseEntity<>(experienceRepository.save(experience), HttpStatus.OK);
    }
    
    //eliminar una experiencia por su id mediante el método DELETE
    @DeleteMapping("/experience/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteSchool(@PathVariable("id") Long id) {
      experienceRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
