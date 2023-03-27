package com.example.jpa.controllers;

import com.example.jpa.Converter.IngredienteConverter;
import com.example.jpa.DAO.IngredienteDAO;
import com.example.jpa.DTO.IngredienteDTO;
import com.example.jpa.model.entity.Ingrediente;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteDAO ingredienteDAO;
    @Autowired
    private IngredienteConverter ingredienteConverter;

    @PostMapping
    public ResponseEntity save(@RequestBody IngredienteDTO dto){
        try {
            Ingrediente saving = ingredienteConverter.ingredienteDTOToIngrediente(dto);
            registrar(saving);
            dto = ingredienteConverter.ingredienteToIngredienteDTO(saving);
            return new ResponseEntity(dto, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping({"{id}"})
    public ResponseEntity update(@PathVariable("id")long id, @RequestBody IngredienteDTO dto){
        try{
            Ingrediente edit=procurarIngredientePorID(id);
            edit.setNome(dto.getNome());
            update(edit);
            dto=ingredienteConverter.ingredienteToIngredienteDTO(edit);

            return ResponseEntity.ok(dto);

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id")long id){
        try{
            deletar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity find(@RequestParam(value = "Nome", required = false)String nome){
        try{
            List<Ingrediente> todos=listarTodos();
            List<Ingrediente> filtrados=new ArrayList<>();
            for(Ingrediente i: todos){
                if(i.getNome().toLowerCase().contains(nome)){
                    filtrados.add(i);
                }
            }
            return ResponseEntity.ok(filtrados);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    // métodos CLÁSSICOS


    public void update(Ingrediente i){
        ingredienteDAO.save(i);
    }

    public List<Ingrediente> listarTodos(){
        return ingredienteDAO.findAll();
    }
    public void deletar(long id){
        ingredienteDAO.deleteById(id);
    }
    public void registrar(Ingrediente i){
        for(Ingrediente j: listarTodos()){
            if(j.getNome().equalsIgnoreCase(i.getNome())){
                return;
            }
        }
        ingredienteDAO.save(i);
    }
    public Ingrediente procurarIngredientePorID(long id)throws Exception{

        Optional<Ingrediente> procurado = ingredienteDAO.findById(id);
        if(procurado.isPresent()){
            return procurado.get();
        }else{
            throw new Exception("ID não encontrado");
        }

    }


}
