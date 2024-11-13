package com.team2.repository;

import com.team2.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad Category
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //Metodo para buscar una categoría por su nombre, devuelve un Optional para manejar la ausencia de resultados
    Optional<Category> findByName(String name);
}
