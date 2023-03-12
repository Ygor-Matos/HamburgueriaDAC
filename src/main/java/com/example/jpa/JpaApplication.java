package com.example.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
                    "\n6-Excluir um ingrediente");
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
                        System.out.println(i.getNome());
                    }
                    break;
                case 6:
                    System.out.print("digite o nome do ingrediente a ser deletado");
                    String ingreF=input.nextLine();
                    ingredienteController.deletar(ingreF);
                    break;
            }
        }while(op>0 && op<7);


    }
}
