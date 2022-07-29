
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
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String position;
    @NotBlank
    private String company;
    @NotBlank
    private String experienceImg;
    @NotBlank
    private String experienceMode;
    @NotBlank
    private String experienceStart;
    @NotBlank
    private String experienceEnd;
    @NotBlank
    private String timeElapsed;

}
