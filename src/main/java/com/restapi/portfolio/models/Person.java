
package com.restapi.portfolio.models;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * @author "Fausto Stradiotto"
 */
//clase Person para el proyecto portfolio
@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person {
    //propiedades 
    //campo id autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //nombre, cadena, longitud 1 - 45
    @NotBlank
    @Size(min = 1, max = 45)
    private String name;
    //url de la imagen de fondo
    @NotBlank
    private String backImg;
    //url de la imagen de perfil
    @NotBlank
    private String profileimg;
    //email
    @NotNull
    @NotBlank
    @Email
    private String email;
    //password
    @NotBlank
    private String password;
    //dirección o ubicación
    @NotBlank
    private String location;
    //url de la imagen de perfil
    @NotBlank
    private String about;
    //detalles de la companía actual
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Company> companys = new HashSet<>();
    
    //detalle de la última institución educativa
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<School> schools = new HashSet<>();
    //Experiencia
    //una persona puede tener muchas experiencias laborales
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Experience> experiences = new HashSet<>();
    
    //Educación
    //una persona puede tener muchos niveles de educación
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Education> educations = new HashSet<>();
    
    //Habilidades
    //una persona puede tener muchas habilidades
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Skill> skills = new HashSet<>();
    
    //Logros
    //una persona puede tener muchos Logros
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Achievement> achievements = new HashSet<>();

    public Person() {
    }

    public Person(String name, String backImg, String profileimg, String email, String password, String location, String about) {
        this.name = name;
        this.backImg = backImg;
        this.profileimg = profileimg;
        this.email = email;
        this.password = password;
        this.location = location;
        this.about = about;
    }

    
    
}
