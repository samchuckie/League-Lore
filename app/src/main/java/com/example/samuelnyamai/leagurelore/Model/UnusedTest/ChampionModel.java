package com.example.samuelnyamai.leagurelore.Model.UnusedTest;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampionsInterface;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO IMPLIMENT A LIST SO WHEN A CHMAPION DATA IS DOWNLOADED REMOVE
public class ChampionModel {
    private static MutableLiveData<ChampionDetails> listMutableLiveData =new MutableLiveData<>();

    public static MutableLiveData<ChampionDetails> getListMutableLiveData() {
        return listMutableLiveData;
    }

    private static Gson gson = new Gson();
    public static void getListMutableLiveData(String[] champion_array) {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        for (String champ : champion_array) {
            Call<ResponseBody> allChampionsCall = championsInterface.getSpecificChampion(champ);
            allChampionsCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject data = jsonObject.getJSONObject("data").getJSONObject(champ);
                        ChampionDetails champion = gson.fromJson(data.toString(), ChampionDetails.class);
                        Log.e("sam" , "The url called is " + call.request().url() );
//                        Log.e("sam", "The title of specific champion is " + champion.getTitle());
//                        Log.e("sam", "The id of the first skin is " + champion.getChampImagesList().get(0).getId());
                     //   Log.e("sam", "The championImage is " + champion.getChampionImage().getFull());
//                        Log.e("sam", champion.getName() + " passive is " + champion.getChampionPassive().getDescription());

                        listMutableLiveData.setValue(champion);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("sam", "Error is" + e.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                }
            });
        }
    }
}
