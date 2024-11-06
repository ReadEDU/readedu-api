package com.team2.repository;

import com.team2.model.entity.Role;
import com.team2.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //Metodo para buscar un usuario por email (sera usado en la autenticacion)
    Optional<Role> findByName(ERole name);
}
