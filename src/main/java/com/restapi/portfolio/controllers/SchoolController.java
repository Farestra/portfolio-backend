
package com.restapi.portfolio.controllers;

import com.restapi.portfolio.exception.ResourceNotFoundException;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.models.School;
import com.restapi.portfolio.repository.PersonRepository;
import com.restapi.portfolio.repository.SchoolRepository;
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
public class SchoolController {
    //importamos repositorio propio y de la entidad padre
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private PersonRepository personRepository;
    
    //obtenemos todas las escuelas para una id de persona mediante el método GET
    @GetMapping("/person/{personId}/school")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<School>> getAllSchoolsByPersonId(@PathVariable(value = "personId") Long personId) {    
        //obtenemos el objeto persona por la id
        Person person = personRepository.findById(personId)
                //si no encuentra la persona lanza una excepción
          .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado una Persona con el id = " + personId));
        //obtiene las habilidades en una lista
        List<School> schools = new ArrayList<>();
      schools.addAll(person.getSchools());
      //retorna las escuelas
      return new ResponseEntity<>(schools, HttpStatus.OK);
    }
    
    //obtenemos una escuela por su id mediante el método GET
    @GetMapping("/school/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<School> getShoolsById(@PathVariable(value = "id") Long id) {
      School school = schoolRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No hay escuela con el id = " + id));
      return new ResponseEntity<>(school, HttpStatus.OK);
    }
    
    //crear una escuela nueva mediante el método POST
    @PostMapping("/person/{personId}/school")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<School> createSkill(@PathVariable(value = "personId") Long personId,
        @RequestBody School schoolRequest) {
      School school = personRepository.findById(personId).map(person -> {
        person.getSchools().add(schoolRequest);
        return schoolRepository.save(schoolRequest);
      }).orElseThrow(() -> new ResourceNotFoundException("No hay persona con id = " + personId));
      return new ResponseEntity<>(school, HttpStatus.CREATED);
    }
    
    //actualizar escuela mediante el método PUT
    @PutMapping("/school/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<School> updateSchool(@PathVariable("id") Long id, @RequestBody School schoolRequest) {
      School school = schoolRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Escuela con id " + id + "no encontrada"));
      //actualizamos la url para la imagen de la institución
      school.setSchoolImg(schoolRequest.getSchoolImg());
      //actualizamos el nombre de la entidad
      school.setSchoolName(schoolRequest.getSchoolName());
      //actializamos la url para la entidad
      school.setSchoolUrl(schoolRequest.getSchoolUrl());
      //guardamos actualizado
      return new ResponseEntity<>(schoolRepository.save(school), HttpStatus.OK);
    }
    
    //eliminar una escuela por su id mediante el método DELETE
    @DeleteMapping("/school/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteSchool(@PathVariable("id") Long id) {
      schoolRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
