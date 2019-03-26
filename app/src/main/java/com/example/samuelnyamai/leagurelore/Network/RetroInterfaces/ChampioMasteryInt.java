package com.example.samuelnyamai.leagurelore.Network.RetroInterfaces;

import com.example.samuelnyamai.leagurelore.data.ChampionsPlayed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChampioMasteryInt {
    @GET("{puud}")
    Call<List<ChampionsPlayed>> getchampionsplayed(@Path("puud") String puud, @Query("api_key") String api_key);
}
