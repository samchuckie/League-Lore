package com.example.samuelnyamai.leagurelore.Network.RetroInterfaces;

import com.example.samuelnyamai.leagurelore.data.Summoner;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginInterface {
    @GET("{summoners_name}")
    Call<Summoner> getPersonData(@Path("summoners_name") String summoners_name, @Query("api_key") String apiKey);
    @GET("{summoners_name}")
    Observable<Summoner> getPersonDatas(@Path("summoners_name") String summoners_name, @Query("api_key") String apiKey);
}
