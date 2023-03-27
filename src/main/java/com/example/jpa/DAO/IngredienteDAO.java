package com.example.jpa.DAO;

import com.example.jpa.model.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteDAO extends JpaRepository<Ingrediente, Long> {
}
