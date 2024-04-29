package com.davidlapes.janca.model.district;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "obec" )
public class Obec {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "kraj_id", nullable = false )
    private Kraj kraj;

    @ManyToOne
    @JoinColumn( name = "okres_id", nullable = false )
    private Okres okres;

    @Column( nullable = false )
    private String kod;

    @Column( nullable = false )
    private String nazev;
}
