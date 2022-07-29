
package com.restapi.portfolio.controllers;


import com.restapi.portfolio.exception.ResourceNotFoundException;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.models.Skill;
import com.restapi.portfolio.repository.PersonRepository;
import com.restapi.portfolio.repository.SkillRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author "Fausto Stradiotto"
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SkillController {
    //importamos repositorio propio y de la entidad padre
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private PersonRepository personRepository;
    
    //obtenemos todas las habilidades para una id de persona mediante el método GET
    @GetMapping("/person/{personId}/skills")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Skill>> getAllSkillsByPersonId(@PathVariable(value = "personId") Long personId) {    
        //obtenemos el objeto persona por la id
        Person person = personRepository.findById(personId)
                //si no encuentra la persona lanza una excepción
          .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado una Persona con el id = " + personId));
        //obtiene las habilidades en una lista
        List<Skill> skills = new ArrayList<>();
      skills.addAll(person.getSkills());
      //retorna las habilidades
      return new ResponseEntity<>(skills, HttpStatus.OK);
    }
    
    //obtenemos una habilidad por su id mediante el método GET
    @GetMapping("/skill/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Skill> getSkillsById(@PathVariable(value = "id") Long id) {
      Skill skill = skillRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No hay habilidad con el id = " + id));
      return new ResponseEntity<>(skill, HttpStatus.OK);
    }
    
    //crear una habilidad nueva mediante el método POST
    @PostMapping("/person/{personId}/skill")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Skill> createSkill(@PathVariable(value = "personId") Long personId,
        @RequestBody Skill skillRequest) {
      Skill skill = personRepository.findById(personId).map(person -> {
        person.getSkills().add(skillRequest);
        return skillRepository.save(skillRequest);
      }).orElseThrow(() -> new ResourceNotFoundException("No hay persona con id = " + personId));
      return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }
    
    //actualizar habilidad mediante el método PUT
    @PutMapping("/skill/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Skill> updateSkill(@PathVariable("id") Long id, @RequestBody Skill skillRequest) {
      Skill skill = skillRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Habilidad con id " + id + "no encontrada"));
      //actualizamos el progreso
      skill.setProgress(skillRequest.getProgress());
      //actualizamos la url de la imagen
      skill.setSkillImg(skillRequest.getSkillImg());
      //actializamos el nombre de la habilidad
      skill.setSkillName(skillRequest.getSkillName());
      return new ResponseEntity<>(skillRepository.save(skill), HttpStatus.OK);
    }
    
    //eliminar una habilidad por su id mediante el método DELETE
    @DeleteMapping("/skill/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable("id") Long id) {
      skillRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
