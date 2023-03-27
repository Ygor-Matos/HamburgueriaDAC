package com.example.jpa.Converter;

import com.example.jpa.DTO.IngredienteDTO;
import com.example.jpa.model.entity.Ingrediente;
import org.springframework.stereotype.Service;

@Service
public class IngredienteConverter {

    public IngredienteDTO ingredienteToIngredienteDTO(Ingrediente i){
        IngredienteDTO convert=new IngredienteDTO();
        convert.setId(i.getId());
        convert.setNome(i.getNome());
        convert.setFazParte(i.getFazParte());
        return convert;
    }

    public Ingrediente ingredienteDTOToIngrediente(IngredienteDTO i){
        Ingrediente convert=new Ingrediente();
        convert.setId(i.getId());
        convert.setNome(i.getNome());
        convert.setFazParte(i.getFazParte());
        return convert;
    }
}
