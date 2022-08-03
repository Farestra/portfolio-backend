
package com.restapi.portfolio.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author "Fausto Stradiotto"
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String school;
    @NotBlank
    private String title;
    @NotBlank
    private String image;
    @NotBlank
    private String career;
    @NotNull
    private int score;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    

}
