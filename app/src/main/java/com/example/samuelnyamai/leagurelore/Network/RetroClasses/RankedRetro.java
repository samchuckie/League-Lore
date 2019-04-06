package com.example.samuelnyamai.leagurelore.Network.RetroClasses;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.BY_NAME;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.BZL_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.EUW_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.HTTP;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.JPN_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.NA_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.OCE_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.RANKED_BASE_URL;

public class RankedRetro {
    // TODO USE ONE GETMETHOD USING SERVER AND WHICH API WE WANT TO ACCCESS EG RANKED, MASTERY OR SUMMONER(server,ranked)
    private static String BASE_URL ;
    public static Retrofit getRankedInfoInstance(String server_choice){
        switch (server_choice){
            case "EUW":
                BASE_URL = HTTP +EUW_BASE_URL + RANKED_BASE_URL;
                break;
            case "NA":
                BASE_URL = HTTP +NA_BASE_URL + RANKED_BASE_URL;
                break;
            case "JPN":
                BASE_URL = HTTP +JPN_BASE_URL + RANKED_BASE_URL;
                break;
            case "OCE":
                BASE_URL = HTTP +OCE_BASE_URL + RANKED_BASE_URL;
                break;
            case "BZL":
                BASE_URL = HTTP +BZL_BASE_URL + RANKED_BASE_URL;
                break;
        }
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
