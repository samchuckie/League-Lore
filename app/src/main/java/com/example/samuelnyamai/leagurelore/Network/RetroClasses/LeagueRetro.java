package com.example.samuelnyamai.leagurelore.Network.RetroClasses;

import com.example.samuelnyamai.leagurelore.Constants.ServerConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.*;

public class LeagueRetro {
    private static String BASE_URL ;
    public static Retrofit getLeagueInstanceServers(String server_choice){
        switch (server_choice){
            case "EUW":
                BASE_URL = HTTP +EUW_BASE_URL + BY_NAME;
                break;
            case "NA":
                BASE_URL = HTTP +NA_BASE_URL + BY_NAME;
                break;
            case "JPN":
                BASE_URL = HTTP +JPN_BASE_URL + BY_NAME;
                break;
            case "OCE":
                BASE_URL = HTTP +OCE_BASE_URL + BY_NAME;
                break;
            case "BZL":
                BASE_URL = HTTP +BZL_BASE_URL + BY_NAME;
                break;
        }
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
