package com.example.samuelnyamai.leagurelore.Network.RetroInterfaces;

import com.example.samuelnyamai.leagurelore.data.AllChampions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChampionsInterface {
    @GET("champion.json")
    Call<AllChampions> getAllChampions();
}
