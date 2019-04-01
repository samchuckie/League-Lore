package com.example.samuelnyamai.leagurelore.Model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampionsInterface;
import com.example.samuelnyamai.leagurelore.Room.ChampionsDatabase;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AltChampionModel {
    private static MutableLiveData<ChampionDetails> listMutableLiveDatas =new MutableLiveData<>();
    public static MutableLiveData<ChampionDetails> getListMutableLiveDatas() {
        return listMutableLiveDatas;
    }
    private static Callback<ResponseBody> call =new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Log.e("sam","The url call is " + call.request().url());
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject data = jsonObject.getJSONObject("data");
                Iterator<String> keys = data.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    JSONObject datas = data.getJSONObject(key);
                    ChampionDetails champion = gson.fromJson(datas.toString(), ChampionDetails.class);

//                    ChampionsDatabase.getChampionDatabseInstance(context).championsDAO().addChampion(champion);

                    Log.e("sam" , "Champion is " + champion.getName());
                    listMutableLiveDatas.setValue(champion);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    };
    private static Gson gson = new Gson();
    public static void getListMutableLiveData(Context context) {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        Call<ResponseBody> allChampionsCall = championsInterface.getAlternativeChampions();
        allChampionsCall.enqueue(call);
    }
    public static void getListMutableLiveData(Context context,String champion) {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        Call<ResponseBody> allChampionsCall = championsInterface.getSpecificChampion(champion);
        allChampionsCall.enqueue(call);
    }
}