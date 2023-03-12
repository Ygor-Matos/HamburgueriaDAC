package com.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteDAO extends JpaRepository<Ingrediente, String> {
}
