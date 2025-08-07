package com.alura.model;


public enum Categoria{
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    DRAMA("Drama","Drama"),
    CRIMEN("Crime","Crimen"),
    COMEDIA("Comedy","Comedia"),
    HORROR("Horror","Horror");

    String categoriaOmdb;
    String categoriaEspanol; 

    Categoria(String categoriaOmdb, String categoriaEspanol){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol= categoriaEspanol;
    }


    public static Categoria fromString(String text) {
        for(Categoria categoria : Categoria.values()){
            if(categoria.categoriaOmdb.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada "+text);
    }

     public static Categoria fromCategoriaEspanol(String text) {
        for(Categoria categoria : Categoria.values()){
            if(categoria.categoriaEspanol.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada "+text);
    }

}