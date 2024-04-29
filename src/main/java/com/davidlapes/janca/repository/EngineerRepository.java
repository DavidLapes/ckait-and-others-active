package com.davidlapes.janca.repository;

import com.davidlapes.janca.model.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, String> {

}
