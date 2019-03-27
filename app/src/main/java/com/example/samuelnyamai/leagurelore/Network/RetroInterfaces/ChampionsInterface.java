package com.example.samuelnyamai.leagurelore.Network.RetroInterfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChampionsInterface {
    @GET("champion.json")
    Call<ResponseBody> getAlternativeChampions();
    @GET("{champion}.json")
    Call<ResponseBody> getAllChampions(@Path("champion") String champion);
}
