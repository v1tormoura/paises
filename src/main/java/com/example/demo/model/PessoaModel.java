package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "pessoas")
@AllArgsConstructor
@NoArgsConstructor
public class PessoaModel extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    @ApiModelProperty(notes = "The ID")
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    @Setter @Getter
    @ApiModelProperty(notes = "The person's  full name")
    private String name;

    @Column(nullable = false, length = 1)
    @Getter @Setter
    @ApiModelProperty(notes = "Gender - just one character (M or F)")
    private String gender;

    @Column(nullable = false, length = 50)
    @Getter @Setter
    @ApiModelProperty(notes = "The city of the person")
    private String city;

    //..new attributes of V2
    @Column(nullable = true, length = 100)
    @Getter @Setter
    @ApiModelProperty(notes = "A valid email address")
    private String email;

    //..relationship with ProfessionModel
    @ManyToOne
    @JoinColumn(name = "pais_id")
    @Setter @Getter
    private PaisModel pais;
}
