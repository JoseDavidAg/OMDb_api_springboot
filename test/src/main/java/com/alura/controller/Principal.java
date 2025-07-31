package com.alura.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.model.DatosSeries;
import com.alura.services.ConsumoAPI;
import com.alura.services.ConvierteDatos;


@SpringBootApplication
public class Principal implements CommandLineRunner{
    public static void main(String [] args ){
        SpringApplication.run(com.alura.controller.Principal.class, args);
    } 

    @Override //metodo que se ejecutara una única vez al iniciar el programa
    public void run(String... args) throws Exception { 
        ConsumoAPI consumoApi = new ConsumoAPI();

        String json = consumoApi.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=84b7b6d1&type");
        ConvierteDatos convierteD = new ConvierteDatos();
        DatosSeries datosS = convierteD.obtenerDatos(json, DatosSeries.class);
        System.err.println(datosS.toString());
        
    }

}
