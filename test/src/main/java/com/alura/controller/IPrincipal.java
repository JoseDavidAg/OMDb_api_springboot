package com.alura.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.alura.model.Categoria;
import com.alura.model.DataTemporadas;
import com.alura.model.DatosSeries;
import com.alura.model.Episodio;
import com.alura.model.Serie;
import com.alura.repository.SerieRepository;
import com.alura.services.ConsumoAPI;
import com.alura.services.ConvierteDatos;

public class IPrincipal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey="+System.getenv("DB_OMDBAPI");
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosSeries> datosSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> series;

    public IPrincipal(SerieRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    4 - Buscar serie por nombre   
                    5 - Top 5 de series    
                    6 - Buscar por categorias   
                    7 - Filtrar por Evaluación y No. de series
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
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 4: 
                    buscarSeriePorNombre();
                    break;
                case 5:
                    buscarTopSeries();
                    break;
                case 6: 
                    buscarSerieCategoria();
                    break;
                case 7:
                    buscarSeriesTemporadas();
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
        mostrarSeriesBuscadas();
        System.out.println("Escribe el nombre de la seria de la cual quieres ver los episodios");
        var nombreSerie = teclado.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()){
            var serieEncontrada = serie.get();
            List<DataTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DataTemporadas datosTemporada = conversor.obtenerDatos(json, DataTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numTemporada(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }
    }

    private void mostrarSeriesBuscadas() {
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DatosSeries datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        //datosSeries.add(datos);
        System.out.println(datos);
    }

    private void buscarSeriePorNombre(){
        System.out.println("Escriba el nombre de la serie que desea buscar: ");
        String nombreSerie = teclado.nextLine().toUpperCase();
        Optional<Serie> serieBuscar = repositorio.findByTituloContainsIgnoreCase(nombreSerie);
        if(serieBuscar.isPresent()){
            System.out.println("La serie encontrada es :"+serieBuscar.get());
        }else{
            System.out.println("Serie no encontrada!");
        }
    }

    private void buscarTopSeries(){
        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
        topSeries.forEach(s-> System.out.println("Serie "+s.getTitulo() +" Evaluación "+s.getEvaluacion()));
    }

    private void buscarSerieCategoria(){
        System.out.println("Escribe el genero/categoria de la seria a buscar: ");
        var genero = teclado.nextLine();
        Categoria categoria = Categoria.fromCategoriaEspanol(genero);
        List<Serie> serieGenero = repositorio.findByGenero(categoria);

        System.out.println("Las series de la categoria "+genero);
        serieGenero.forEach(m-> System.out.println("Nombre: "+m.getTitulo()+" Genero: "+m.getGenero()));
    }

  
    private void buscarSeriesTemporadas(){
        System.out.println("Ingresa la calificación mínima para una serie: ");
            int calificacion = teclado.nextInt();
        System.out.println("Ingresa el máximo de temporadas de la serie: ");
            int numTe = teclado.nextInt();

        List<Serie> resultado = repositorio.buscarTemporadasValoracion(numTe, calificacion);
        resultado.forEach(m-> System.out.println("Nombre: "+m.getTitulo()+" Calificación : "+m.getEvaluacion()+" No.Temporadas: "+m.getTotalTemporadas()));


    }

}