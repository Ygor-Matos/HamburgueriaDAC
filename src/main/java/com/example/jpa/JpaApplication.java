package com.example.jpa;

import com.example.jpa.controllers.HamburguerController;
import com.example.jpa.controllers.IngredienteController;
import com.example.jpa.model.entity.Hamburguer;
import com.example.jpa.model.entity.Ingrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    @Autowired
    private HamburguerController hamburguerController;
    @Autowired
    private IngredienteController ingredienteController;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }


    public void run(String... args) throws Exception {
        Scanner input=new Scanner(System.in);
        int op=10;
        do{
            System.out.println("1-Registrar um Hamburguer" +
                    "\n2-Listar Cardapio" +
                    "\n3-Excluir um hamburguer" +
                    "\n4-Registrar um Ingrediente" +
                    "\n5-Listar ingredientes" +
                    "\n6-Excluir um ingrediente" +
                    "\n7-Editar um Hamburguer" +
                    "\n8-Editar um Ingrediente");
            op=Integer.parseInt(input.nextLine());
            switch (op){
                case 1:
                    //registrando um novo hamburguer
                    System.out.print("Digite o nome do Hamburguer");
                    String nomeH=input.nextLine();
                    System.out.print("Digite o preço do Hamburguer");
                    float pre=Float.parseFloat(input.nextLine());
                    int quantidade=10;
                    do{
                        System.out.print("quanto ingredientes o Hamburguer vai ter?\n minimo=1 Máximo 5");
                        quantidade=Integer.parseInt(input.nextLine());

                    }while(quantidade>5 && quantidade<1);

                    List<Ingrediente> ingredientes = new ArrayList<>();
                    for(int i=0;i<quantidade;i++){
                        System.out.print("Digite o nome do ingrediente");
                        Ingrediente ing=new Ingrediente(input.nextLine());
                        ingredienteController.registrar(ing);
                        ingredientes.add(ing);
                    }
                    Hamburguer h=new Hamburguer(nomeH,pre,ingredientes);
                    hamburguerController.registrar(h);
                    break;
                case 2:
                    for(Hamburguer x: hamburguerController.listarTodos()){
                        System.out.println(x.toString()+"\n");
                    }
                    break;
                case 3:
                    System.out.print("digite o ID do hamburguer a ser excluido");
                    long excluir=Long.parseLong(input.nextLine());
                    hamburguerController.deletar(excluir);
                    break;
                case 4:
                    System.out.print("digite qual ingrediente deseja adicionar ao BD");
                    Ingrediente adicionar=new Ingrediente(input.nextLine());
                    ingredienteController.registrar(adicionar);
                    break;
                case 5:
                    for(Ingrediente i:ingredienteController.listarTodos()){
                        System.out.println(i.toString());
                    }
                    break;
                case 6:
                    System.out.print("digite o ID do ingrediente a ser deletado");
                    Long ingreF=Long.parseLong(input.nextLine());
                    ingredienteController.deletar(ingreF);
                    break;
                case 7:
                    System.out.println("Digite o ID do Hamburguer que deseja editar:");
                    Hamburguer editar=new Hamburguer();
                    int hamb=Integer.parseInt(input.nextLine());
                    try{
                        editar=hamburguerController.procurarHamburguerPorID(hamb);
                        System.out.print(editar.toString());
                    }catch (Exception e){
                        System.out.print(e.getMessage());
                    }
                    System.out.println("1-editar o nome\n 2-editar o preço");
                    int edit=Integer.parseInt(input.nextLine());
                    switch (edit){
                        case 1:
                            System.out.print("Digite o novo nome do Hamburguer:");
                            String newName=input.nextLine();
                            editar.setNome(newName);
                            break;
                        case 2:
                            System.out.print("Digite o preço do novo hamburguer:");
                            float novoPrec=Float.parseFloat(input.nextLine());
                            editar.setPreco(novoPrec);
                            break;
                    }
                    hamburguerController.registrar(editar);
                    break;
                case 8:
                    System.out.print("Digite o ID do Ingrediente que deseja editar:");
                    Long id=Long.parseLong(input.nextLine());
                    Ingrediente ingedit= new Ingrediente();
                    try{
                        ingedit= ingredienteController.procurarIngredientePorID(id);
                    }catch(Exception e){
                        System.out.print(e.getMessage());
                    }
                    System.out.print(ingedit.toString());
                    System.out.print("Digite o novo nome:");
                    ingedit.setNome(input.nextLine());
                    ingredienteController.registrar(ingedit);
                    break;
            }
        }while(op>0 && op<9);


    }
}
