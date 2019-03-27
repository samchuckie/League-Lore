package com.example.samuelnyamai.leagurelore.Network.RetroClasses;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPIONS_BASE_URL;

public class ChampionRetro {
    public static Retrofit getAllChampionInstance(){
        return new Retrofit.Builder()
                //.baseUrl(S_CHAMPION_BASE_URL)
                .baseUrl(CHAMPIONS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
