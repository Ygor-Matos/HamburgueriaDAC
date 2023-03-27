package com.example.jpa.DAO;

import com.example.jpa.model.entity.Hamburguer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HamburguerDAO extends JpaRepository<Hamburguer,Long> {
}
