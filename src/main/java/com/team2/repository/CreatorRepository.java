package com.team2.repository;

import com.team2.model.entity.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {

    Optional<Creator> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    //Metodo para verificar si ya existe un autor con el mismo nombre y apellido.
    boolean existsByFirstNameAndLastNameAndUserIdNot(String firstName, String lastName, Integer userId);
}
