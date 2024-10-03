package com.team2.repository;

import com.team2.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByFirstNameAndLastName(String name, String lastName);

    boolean existsByFirstNameAndLastNameAndUserIdNot(String firstName, String lastName, Integer id);
}
