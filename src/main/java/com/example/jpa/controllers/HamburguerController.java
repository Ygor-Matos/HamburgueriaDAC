package com.example.jpa.controllers;

import com.example.jpa.Converter.HamburguerConverter;
import com.example.jpa.DAO.HamburguerDAO;
import com.example.jpa.DTO.HamburguerDTO;
import com.example.jpa.model.entity.Hamburguer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@RestController
@RequestMapping("/api/Hamburguer")
public class HamburguerController {

    @Autowired
    private HamburguerDAO hamburguerDAO;
    @Autowired
    private HamburguerConverter hamburguerConverter;

    @PostMapping
    public ResponseEntity save(@RequestBody HamburguerDTO DTO){
        try{
            Hamburguer saved = hamburguerConverter.DTOtoHamburguer(DTO);
            registrar(saved);
            DTO=hamburguerConverter.HamburguertoDTO(saved);

            return new ResponseEntity(DTO, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id")long id, @RequestBody HamburguerDTO dto){
        try{
            //pegando o objeto original através do ID
            Hamburguer update= procurarHamburguerPorID(id);
            //atualizando os atributos
            update.setPreco(dto.getPreco());
            //update.setIngredientes(dto.getIngredientes());
            update.setNome(dto.getNome());
            update(update);
            dto=hamburguerConverter.HamburguertoDTO(update);

            return ResponseEntity.ok(dto);

        }catch (Exception e){
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
            List<Hamburguer> todos= hamburguerDAO.findAll();
            List<Hamburguer> filtrados=new ArrayList<>();
            for(Hamburguer j:todos){
                if(j.getNome().toLowerCase().contains(nome.toLowerCase(Locale.ROOT))){
                    filtrados.add(j);
                }
            }
            return ResponseEntity.ok(filtrados);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Métodos clássicos
    public void deletar(long id){
        hamburguerDAO.deleteById(id);
    }

    public void registrar(Hamburguer h){
        for(Hamburguer i: listarTodos()){
            if(i.getId()==h.getId()){
                return;
            }
        }
        hamburguerDAO.save(h);
    }

    public List<Hamburguer> listarTodos(){
        return hamburguerDAO.findAll();
    }

    public Hamburguer procurarHamburguerPorID(long id)throws Exception{
        Optional<Hamburguer> procura=hamburguerDAO.findById(id);
        if(procura.isPresent()){
            return procura.get();
        }else {
            throw new Exception("esse Hamburguer não existe");
        }

    }
    public void update(Hamburguer h){
        hamburguerDAO.save(h);
    }
}
