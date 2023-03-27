package com.example.jpa.model.entity;

import com.example.jpa.model.entity.Hamburguer;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ingrediente {

    @Id @GeneratedValue
    private long id;



    private String nome;
    @ManyToMany(mappedBy = "ingredientes")
    private List<Hamburguer> fazParte;

    public List<Hamburguer> getFazParte(){
        return fazParte;
    }

    public void setFazParte(List<Hamburguer> fazParte){
        this.fazParte=fazParte;
    }

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
    public String toString(){
        return "Nome:"+nome+"\nID:"+id;
    }
}
