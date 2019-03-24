package com.example.samuelnyamai.leagurelore.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampionsInterface;
import com.example.samuelnyamai.leagurelore.data.AllChampions;
import com.example.samuelnyamai.leagurelore.data.Champion_details;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChampionModel {
    static Call<ResponseBody> allChampionsCall;
    private static MutableLiveData<AllChampions> allChampionsMutableLiveData =new MutableLiveData<>();

    public static MutableLiveData<AllChampions> getAllChampionsMutableLiveData(String[] servers) {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        allChampionsCall= championsInterface.getAllChampions("Aatrox");

        allChampionsCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONObject data = jsonObject.getJSONObject("data").getJSONObject("Aatrox");
                    String title = data.getString("title");
                    Log.e("sam","Title is" + title);
                    Gson gson= new Gson();
                    Champion_details championdetails = gson.fromJson(data.toString() , Champion_details.class);
                    Log.e("sam" , "The title of specific champion is "+ championdetails.getTitle());
                    Log.e("sam" , "The id of the first skin is "+ championdetails.getChampImagesList().get(0).getId());
                    Log.e("sam" , "The championImage is " + championdetails.getChampionImage().getFull());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("sam","Error is" + e.toString()) ;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("sam","Call is" + call.request().url());

            }
        });
        return allChampionsMutableLiveData;
    }
}
