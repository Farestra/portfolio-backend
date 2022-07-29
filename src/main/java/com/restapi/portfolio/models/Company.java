
package com.restapi.portfolio.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author "Fausto Stradiotto"
 */
//clase para el elemento company de la clase Person

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "companys")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String companyName;
    @NotBlank
    private String companyImg;
    @NotBlank
    private String companyUrl;


}
