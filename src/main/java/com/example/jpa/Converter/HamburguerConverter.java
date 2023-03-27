package com.example.jpa.Converter;

import com.example.jpa.DTO.HamburguerDTO;
import com.example.jpa.model.entity.Hamburguer;
import org.springframework.stereotype.Service;

@Service
public class HamburguerConverter {

    //convertendo um DTO para Hamburguer
    public Hamburguer DTOtoHamburguer(HamburguerDTO h){
        Hamburguer convert=new Hamburguer();
        convert.setId(h.getId());
        convert.setNome(h.getNome());
        convert.setPreco(h.getPreco());
        convert.setIngredientes(h.getIngredientes());
        return convert;
    }

    //convertendo um hamburguer para DTO
    public HamburguerDTO HamburguertoDTO(Hamburguer h){
        HamburguerDTO convert=new HamburguerDTO();
        convert.setId(h.getId());
        convert.setIngredientes(h.getIngredientes());
        convert.setNome(h.getNome());
        convert.setPreco(h.getPreco());
        return convert;
    }
}
