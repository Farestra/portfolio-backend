
package com.restapi.portfolio.controllers;

import com.restapi.portfolio.exception.ResourceNotFoundException;
import com.restapi.portfolio.models.Company;
import com.restapi.portfolio.models.Person;
import com.restapi.portfolio.repository.CompanyRepository;
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
public class CompanyController {
    //importamos repositorio propio y de la entidad padre
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PersonRepository personRepository;
    
    //obtenemos todas los company para una id de persona mediante el método GET
    @GetMapping("/person/{personId}/company")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Company>> getAllCompanysByPersonId(@PathVariable(value = "personId") Long personId) {    
        //obtenemos el objeto persona por la id
        Person person = personRepository.findById(personId)
                //si no encuentra la persona lanza una excepción
          .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado una Persona con el id = " + personId));
        //obtiene los logros en una lista
        List<Company> companys = new ArrayList<>();
      companys.addAll(person.getCompanys());
      //retorna los logros
      return new ResponseEntity<>(companys, HttpStatus.OK);
    }
    
    //obtenemos un logro por su id mediante el método GET
    @GetMapping("/company/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long id) {
      Company company = companyRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No hay compañía con el id = " + id));
      return new ResponseEntity<>(company, HttpStatus.OK);
    }
    
    //crear un logro nuevo mediante el método POST
    @PostMapping("/person/{personId}/company")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Company> createCompany(@PathVariable(value = "personId") Long personId,
        @RequestBody Company companyRequest) {
      Company company = personRepository.findById(personId).map(person -> {
        person.getCompanys().add(companyRequest);
        return companyRepository.save(companyRequest);
      }).orElseThrow(() -> new ResourceNotFoundException("No hay persona con id = " + personId));
      return new ResponseEntity<>(company, HttpStatus.CREATED);
    }
    
    //actualizar educación mediante el método PUT
    @PutMapping("/company/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id, @RequestBody Company companyRequest) {
      Company company = companyRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Logro con id " + id + "no encontrado"));
      company.setName(companyRequest.getName());
      company.setImage(companyRequest.getImage());
      company.setUrl(companyRequest.getUrl());
      //guardamos actualizado
      return new ResponseEntity<>(companyRepository.save(company), HttpStatus.OK);
    }
    
    //eliminar una educación por su id mediante el método DELETE
    @DeleteMapping("/company/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") Long id) {
      companyRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
