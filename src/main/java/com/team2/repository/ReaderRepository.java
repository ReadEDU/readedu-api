package com.team2.repository;

import com.team2.model.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    //Metodo para verificar si ya existe un cliente con el mismo nombre y apellido, excepto el usuario actual
    boolean existsByFirstNameAndLastNameAndUserIdNot(String firstName, String lastName, Integer userId);
}
