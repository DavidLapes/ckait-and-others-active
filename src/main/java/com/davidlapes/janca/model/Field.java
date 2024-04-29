package com.davidlapes.janca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table( name = "fields" )
public class Field {

    @Id
    private String id;
    @Column(nullable = false)
    private String description;
    @ManyToMany( mappedBy = "fields" )
    private Set<Engineer> engineers;

    @Override
    public boolean equals( Object o ) {
        // TODO: checking for instance
        if ( !( o instanceof Field f )) {
            return false;
        }
        return id.equals( f.id );
    }
}
