package com.example.proyectofinal.models;

import java.util.HashMap;
import java.util.Map;

public class Directorio {
    private static  Directorio instance;
    private Map<String,String> apiUrls;

    //constructor privado para evitar instancias directas
    private Directorio(){

         apiUrls = new HashMap<>(); //se crea la lista de las apis

        apiUrls.put("api1","http://ticketramateatros.000webhostapp.com/api/carteleras");
        apiUrls.put("api2","http://ticketramateatros.000webhostapp.com/api/eventos");
        apiUrls.put("api3","http://ticketramateatros.000webhostapp.com/api/validacion");
        apiUrls.put("api4","http://ticketramateatros.000webhostapp.com/api/usuarios");
        apiUrls.put("api5","http://ticketramateatros.000webhostapp.com/api/precioseventos");
        apiUrls.put("api6","http://ticketramateatros.000webhostapp.com/api/asientoseventos");
        apiUrls.put("api7","http://ticketramateatros.000webhostapp.com/api/asientosactualizados");


    }

    public static Directorio getInstance(){
        if(instance == null){
            instance = new Directorio();
        }

        return instance;
    }


    public String getApiUrls(String apiName){
        String apiUrl= apiUrls.get(apiName);
        return  apiUrl;
    }

    public String getApiUrlsId(String apiName,int  apiId){
        String apiUrl= apiUrls.get(apiName);

        if(apiUrl != null){
            apiUrl = apiUrl+"/"+apiId;
        }

        return  apiUrl;
    }





}
