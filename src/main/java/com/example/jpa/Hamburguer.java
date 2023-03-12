package com.example.jpa;

import jakarta.persistence.*;
import org.hibernate.id.IncrementGenerator;

import java.util.List;

@Entity
@Table(name ="HAMBURGUERES")
public class Hamburguer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "Hamburguer_ingrediente")
    private List<Ingrediente> ingredientes;
    private String nome;

    private float preco;

    public String toString(){

        String ingre="";
        for(Ingrediente x: ingredientes){
            ingre+=x.getNome()+", ";
        }
    return "Nome:"+nome+"\nPreço:"+preco+"\nIngredientes:"+ingre;

    }
    public Hamburguer(String nome, float preco,List<Ingrediente> ingredientes){
        setNome(nome);
        setPreco(preco);
        setIngredientes(ingredientes);

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public void setIngredientes(List<Ingrediente> ig){
        this.ingredientes=ig;
    }
    public void setId(long id) {
        this.id = id;
    }


    public Hamburguer() {
    }
}
