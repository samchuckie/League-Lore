package com.example.samuelnyamai.leagurelore.Network.RetroClasses;

import com.example.samuelnyamai.leagurelore.Constants.ServerConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeagueRetro {
    private static  String BASE_URL ;
    public static Retrofit getLeagueInstanceServers(String server_choice){
        switch (server_choice){
            case "EUW":
                BASE_URL =ServerConstants.EUW_BASE_URL;
                break;
            case "NA":
                BASE_URL =ServerConstants.NA_BASE_URL;
                break;
            case "JPN":
                BASE_URL =ServerConstants.JPN_BASE_URL;
                break;
            case "OCE":
                BASE_URL =ServerConstants.OCE_BASE_URL;
                break;
            case "BZL":
                BASE_URL =ServerConstants.BZL_BASE_URL;
                break;
        }
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public static Retrofit getLeagueInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
