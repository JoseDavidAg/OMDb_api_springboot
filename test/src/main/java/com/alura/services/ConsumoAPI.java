package com.alura.services;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConsumoAPI {
    
    public String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest reques = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse <String> response = client.send(reques,HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            return json;
        
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        
    }
}
