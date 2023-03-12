package com.example.jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ingrediente {

    @Id @GeneratedValue
    private long id;
    private String nome;
    @ManyToMany(mappedBy = "ingredientes")
    List<Hamburguer> fazParte;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Ingrediente() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ingrediente(String nome){
        this.nome=nome;
    }

}
