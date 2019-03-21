package com.example.samuelnyamai.leagurelore.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampionsInterface;
import com.example.samuelnyamai.leagurelore.data.AllChampions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionModel {
    private static MutableLiveData<AllChampions> allChampionsMutableLiveData =new MutableLiveData<>();

    public static MutableLiveData<AllChampions> getAllChampionsMutableLiveData() {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        Call<AllChampions> allChampionsCall = championsInterface.getAllChampions();
        allChampionsCall.enqueue(new Callback<AllChampions>() {
            @Override
            public void onResponse(Call<AllChampions> call, Response<AllChampions> response) {
                Log.e("sam","Call is "+ call.request().url().toString());
                Log.e("sam" , "Version is " + response.body().getVersion());
                Log.e("sam","Type is "  + response.body().getType());
                Log.e("sam","Making a call all time");
                Log.e("sam","First champion data is " + response.body().getData().get(1).toString());
               // allChampionsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AllChampions> call, Throwable t) {
                Log.e("sam" , "Error occured is " + t.getMessage());
                //allChampionsMutableLiveData.setValue(null);

            }
        });
        return allChampionsMutableLiveData;
    }
}
