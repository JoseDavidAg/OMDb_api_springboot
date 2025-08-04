package com.alura.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alura.model.DataTemporadas;
import com.alura.model.DatosSeries;
import com.alura.services.ConsumoAPI;
import com.alura.services.ConvierteDatos;

public class IPrincipal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=84b7b6d1";
    private final ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosSeries getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        DatosSeries datos = conversor.obtenerDatos(json, DatosSeries.class);
        return datos;
    }
    private void buscarEpisodioPorSerie() {
        DatosSeries datosSerie = getDatosSerie();
        List<DataTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
            var json = consumoApi.obtenerDatos(URL_BASE + datosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DataTemporadas datosTemporada = conversor.obtenerDatos(json, DataTemporadas.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
    private void buscarSerieWeb() {
        DatosSeries datos = getDatosSerie();
        System.out.println(datos);
    }


}