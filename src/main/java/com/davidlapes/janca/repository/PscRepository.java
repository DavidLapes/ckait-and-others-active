package com.davidlapes.janca.repository;

import com.davidlapes.janca.model.district.Psc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PscRepository extends JpaRepository<Psc, Integer> {

    @Query("SELECT p FROM Psc p WHERE p.psc = ?1 AND p.nazev = ?2")
    Psc findByPscAndName( String psc, String nazev );
}
