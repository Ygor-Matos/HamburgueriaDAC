package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class HamburguerController {

    @Autowired
    private HamburguerDAO hamburguerDAO;

    public void testando(){
        for (int i=0;i<4;i++){
            Hamburguer x = new Hamburguer();
            hamburguerDAO.save(x);
        }
    }
    public void deletar(long id){
        hamburguerDAO.deleteById(id);
    }

    public void registrar(Hamburguer h){
        hamburguerDAO.save(h);
    }

    public List<Hamburguer> listarTodos(){
        return hamburguerDAO.findAll();
    }
}
