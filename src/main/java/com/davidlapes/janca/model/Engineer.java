package com.davidlapes.janca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table( name = "engineers" )
public class Engineer {

    @Id
    private String id;
    private String detailUrl;
    @Column( nullable = false )
    private String firstName;
    @Column( nullable = false )
    private String lastName;
    private String title;
    @Column( nullable = false )
    private String address;
    @Column( nullable = false )
    private String street;
    @Column( nullable = false )
    private String city;
    @Column( nullable = false )
    private String zip;
    private String svobIng;
    private String personalPhone;
    private String personalEmail;
    private LocalDate dateOfBirth;
    private String companyName;
    private String companyAddress;
    private String companyEmail;
    private String companyPhone;
    private String companyWww;
    private String companyIco;
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "engineers_fields",
            joinColumns = @JoinColumn( name = "member_id" ),
            inverseJoinColumns = @JoinColumn( name = "field_id" )
    )
    private Set<Field> fields;
}
