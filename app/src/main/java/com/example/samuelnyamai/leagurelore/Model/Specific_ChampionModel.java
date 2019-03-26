package com.example.samuelnyamai.leagurelore.Model;

import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionsPlayedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.RankedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampioMasteryInt;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.RankedInterface;
import com.example.samuelnyamai.leagurelore.data.ChampionsPlayed;
import com.example.samuelnyamai.leagurelore.data.SummonerRankedInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Specific_ChampionModel {
    RankedInterface rankedInterface = RankedRetro.getRankedInfoInstance().create(RankedInterface.class);
    ChampioMasteryInt championsInterface = ChampionsPlayedRetro.getAllChampionMasteryInstance().create(ChampioMasteryInt.class);
    Call<List<SummonerRankedInfo>> listCall =  rankedInterface.
            getsummoneranked("3ckvH8FwV3ZFIbGsJGDI5XhUjnFM_A_IjcFAFgz5FvwJEww","RGAPI-8507538e-bb9b-4b56-866c-f7145927471b");
    Call<List<ChampionsPlayed>> listCaller = championsInterface.
            getchampionsplayed("3ckvH8FwV3ZFIbGsJGDI5XhUjnFM_A_IjcFAFgz5FvwJEww","RGAPI-8507538e-bb9b-4b56-866c-f7145927471b");
    public Specific_ChampionModel(){
        listCall.enqueue(new Callback<List<SummonerRankedInfo>>() {
            @Override
            public void onResponse(Call<List<SummonerRankedInfo>> call, Response<List<SummonerRankedInfo>> response) {
                Log.e("sam", "The summoner name is " + response.body().get(0).getLeagueName());

            }

            @Override
            public void onFailure(Call<List<SummonerRankedInfo>> call, Throwable t) {
                Log.e("sam", "The error is " + t.getMessage());
            }
        });

        listCaller.enqueue(new Callback<List<ChampionsPlayed>>() {
            @Override
            public void onResponse(Call<List<ChampionsPlayed>> call, Response<List<ChampionsPlayed>> response) {
                Log.e("sam", "The champion id is " + response.body().get(0).getChampionId());
            }

            @Override
            public void onFailure(Call<List<ChampionsPlayed>> call, Throwable t) {
                Log.e("sam", "The error is " + t.getMessage());
            }
        });
    }
}
