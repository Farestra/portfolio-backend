
package com.restapi.portfolio.controllers;

import com.restapi.portfolio.exception.ResourceNotFoundException;
import com.restapi.portfolio.models.Education;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.repository.EducationRepository;
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
public class EducationController {
    //importamos repositorio propio y de la entidad padre
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private PersonRepository personRepository;
    
    //obtenemos todas las educaciones para una id de persona mediante el método GET
    @GetMapping("/person/{personId}/education")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Education>> getAllEducationsByPersonId(@PathVariable(value = "personId") Long personId) {    
        //obtenemos el objeto persona por la id
        Person person = personRepository.findById(personId)
                //si no encuentra la persona lanza una excepción
          .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado una Persona con el id = " + personId));
        //obtiene las educaciones en una lista
        List<Education> educations = new ArrayList<>();
      educations.addAll(person.getEducations());
      //retorna las educaciones
      return new ResponseEntity<>(educations, HttpStatus.OK);
    }
    
    //obtenemos una educación por su id mediante el método GET
    @GetMapping("/education/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Education> getEducationById(@PathVariable(value = "id") Long id) {
      Education education = educationRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No hay educación con el id = " + id));
      return new ResponseEntity<>(education, HttpStatus.OK);
    }
    
    //crear una educación nueva mediante el método POST
    @PostMapping("/person/{personId}/education")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Education> createEducation(@PathVariable(value = "personId") Long personId,
        @RequestBody Education educationRequest) {
      Education education = personRepository.findById(personId).map(person -> {
        person.getEducations().add(educationRequest);
        return educationRepository.save(educationRequest);
      }).orElseThrow(() -> new ResourceNotFoundException("No hay persona con id = " + personId));
      return new ResponseEntity<>(education, HttpStatus.CREATED);
    }
    
    //actualizar educación mediante el método PUT
    @PutMapping("/education/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Education> updateEducation(@PathVariable("id") Long id, @RequestBody Education educationRequest) {
      Education education = educationRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Educación con id " + id + "no encontrada"));
      education.setSchool(educationRequest.getSchool());
      education.setTitle(educationRequest.getTitle());
      education.setEducationImg(educationRequest.getEducationImg());
      education.setCareer(educationRequest.getCareer());
      education.setScore(educationRequest.getScore());
      education.setEducationStart(educationRequest.getEducationStart());
      education.setEducationEnd(educationRequest.getEducationEnd());
      
      //guardamos actualizado
      return new ResponseEntity<>(educationRepository.save(education), HttpStatus.OK);
    }
    
    //eliminar una educación por su id mediante el método DELETE
    @DeleteMapping("/education/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteEducation(@PathVariable("id") Long id) {
      educationRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
