package com.davidlapes.janca.model.district;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "psc" )
public class Psc {

    @Id
    private Integer id;
    private String psc;
    private String nazev;
    private String nazevPosta;
    private String nazevOkres;

    @ManyToOne
    @JoinColumn( name = "okres_id" )
    private Okres okres;

    @ManyToOne
    @JoinColumn( name = "kraj_id" )
    private Kraj kraj;
}
