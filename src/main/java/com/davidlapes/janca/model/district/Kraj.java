package com.davidlapes.janca.model.district;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kraj")
public class Kraj {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String kod;

    @Column(nullable = false)
    private String nazev;
}
