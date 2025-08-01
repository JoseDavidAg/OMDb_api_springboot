package com.alura.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alura.model.DataTemporadas;
import com.alura.model.DatosSeries;
import com.alura.services.ConsumoAPI;
import com.alura.services.ConvierteDatos;

public class IPrincipal {
    private Scanner sc = new Scanner(System.in);
    private final String URL_A = "http://www.omdbapi.com/?";
    private final String API_K = "&apikey=84b7b6d1";
    ConsumoAPI consumoApi = new ConsumoAPI();
    ConvierteDatos convierteD = new ConvierteDatos();
    
    public void mostrarMenu(){
        
        System.out.println("Escribe el nombre de la serie que desea visualizar: ");
        String nombre = sc.nextLine();
        String url = URL_A + "t="+nombre.replace(" ","+")+API_K;
        
        String json = consumoApi.obtenerDatos(url);
      
        DatosSeries datos = convierteD.obtenerDatos(json, DatosSeries.class);

        List<DataTemporadas>temporadas = new ArrayList<>();
        for(int i=1; i<=datos.totalTemporadas(); i++){
            json = consumoApi.obtenerDatos(url +"&season="+i);
            
            DataTemporadas temporada = convierteD.obtenerDatos(json, DataTemporadas.class);
            temporadas.add(temporada);
        }

        //temporadas.forEach(System.out::println);

        /* //imprimir solo el nombre de cada episodio

        for(int i=1; i<=datos.totalTemporadas(); i++){
            List<DataEpisodio> episodio = temporadas.get(i-1).episodios();
            for(int j =0 ; j< episodio.size(); j++){
                System.out.println(episodio.get(j).titulo());
            }
        }*/
        
        temporadas.forEach(t-> t.episodios().forEach(e -> System.out.println(e.titulo())));
        
    }

}
