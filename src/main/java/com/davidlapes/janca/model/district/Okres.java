package com.davidlapes.janca.model.district;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "okres" )
public class Okres {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "kraj_id", nullable = false )
    private Kraj kraj;

    @Column( nullable = false )
    private String kod;

    @Column( nullable = false )
    private String nazev;
}
