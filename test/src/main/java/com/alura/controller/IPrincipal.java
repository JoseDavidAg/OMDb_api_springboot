package com.alura.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.alura.model.DataEpisodio;
import com.alura.model.DataTemporadas;
import com.alura.model.DatosSeries;
import com.alura.model.Episodio;
import com.alura.services.ConsumoAPI;
import com.alura.services.ConvierteDatos;

public class IPrincipal {
    private final Scanner sc = new Scanner(System.in);
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
        
        //Imprime el nombre de todos los episodios
        //temporadas.forEach(t-> t.episodios().forEach(e -> System.out.println(e.titulo())));

        //Convertir toda la información en objetos de tipo DataEpisodio -> ver serie, ver temporadas, ver todos los episodios

        List<DataEpisodio> episodios = temporadas.stream()  
            .flatMap(t -> t.episodios().stream())
            .toList();

        //top 5 episodios
        System.out.println("Top 5 episodios de la serie:");
        episodios.stream().filter(m -> !m.evaluacion().equalsIgnoreCase("N/A")).sorted(Comparator.comparing(DataEpisodio::evaluacion).reversed()).limit(5).forEach(System.out::println);

        //Convertir los datos de tipo Episodio
        List<Episodio> episodiosM =  temporadas.stream()   
            .flatMap(t -> t.episodios().stream()
                .map(d -> new Episodio(t.numTemporada(), d))).collect(Collectors.toList());
            
        episodiosM.forEach(System.out::println);
        
    }

}
