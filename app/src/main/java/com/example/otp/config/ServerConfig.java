package com.example.otp.config;

import com.example.otp.rest.ApiService;
import com.example.otp.rest.ClientService;

public class ServerConfig {
    // .0.2.2 ini adalah localhost.
    //192.168.100.5
    //192.168.43.210
    //192.168.1.193

    //https://marketresto.000webhostapp.com/public/
    //https://topapp.id/marketresto/api/v1/

    //private static final String BASE_URL_API = "http://172.16.140.87/marketresto/api/v1/";
    //private static final String BASE_URL_API = "https://topapp.id/marketresto/api/v1/";
    private static final String BASE_URL_API = "http://192.168.100.31:8080/ommapawon/";
    // Mendeklarasikan Interface BaseApiService
    public static ApiService getAPIService(){
        return ClientService.konekRetrofit(BASE_URL_API).create(ApiService.class);
    }
}
