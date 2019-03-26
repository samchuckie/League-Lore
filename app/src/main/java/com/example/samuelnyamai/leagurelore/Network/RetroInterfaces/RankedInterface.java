package com.example.samuelnyamai.leagurelore.Network.RetroInterfaces;

import com.example.samuelnyamai.leagurelore.data.SummonerRankedInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RankedInterface {
    @GET("{puud}")
    Call<List<SummonerRankedInfo>> getsummoneranked(@Path("puud") String puud, @Query("api_key") String api_key);
}
