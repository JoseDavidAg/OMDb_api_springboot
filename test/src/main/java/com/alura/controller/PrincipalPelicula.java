package com.alura.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class PrincipalPelicula {
    
    public String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest reques = HttpRequest.newBuilder().uri(URI.create("jij")).build();
        HttpResponse response = null;
        try {
            response= client.send(reques,HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        String json = response.toString();
        return json;
        
    }
}
