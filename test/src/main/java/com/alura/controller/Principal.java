package com.alura.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Principal implements CommandLineRunner{
    public static void main(String [] args ){
        SpringApplication.run(com.alura.controller.Principal.class, args);
    } 

    @Override //metodo que se ejecutara una única vez al iniciar el programa
    public void run(String... args) throws Exception { 
        IPrincipal principal = new IPrincipal();
        principal.muestraElMenu();;
    
        
       

        
    }

}
