package com.alura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.controller.IPrincipal;
import com.alura.repository.SerieRepository;


@SpringBootApplication
public class Principal implements CommandLineRunner{

    @Autowired//inyeccion de dependencia
    private SerieRepository serie;

        public static void main(String [] args ){
        SpringApplication.run(com.alura.Principal.class, args);
    } 

    @Override //metodo que se ejecutara una única vez al iniciar el programa
    public void run(String... args) throws Exception { 
        IPrincipal principal = new IPrincipal(serie);
        principal.muestraElMenu();
    
        
       

        
    }

}
