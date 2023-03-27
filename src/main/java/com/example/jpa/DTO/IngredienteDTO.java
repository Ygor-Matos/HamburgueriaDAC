package com.example.jpa.DTO;

import com.example.jpa.model.entity.Hamburguer;

import java.util.List;

public class IngredienteDTO {

    private long id;
    private String nome;

    private List<Hamburguer> fazParte;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Hamburguer> getFazParte() {
        return fazParte;
    }

    public void setFazParte(List<Hamburguer> fazParte) {
        this.fazParte = fazParte;
    }
}
