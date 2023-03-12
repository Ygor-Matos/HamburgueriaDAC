package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class IngredienteController {

    @Autowired
    private IngredienteDAO ingredienteDAO;

    public void deletar(String nome){
        ingredienteDAO.deleteById(nome);
    }
    public void registrar(Ingrediente i){
        ingredienteDAO.save(i);
    }

    public List<Ingrediente> listarTodos(){
        return ingredienteDAO.findAll();
    }


}
