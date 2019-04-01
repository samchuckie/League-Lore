package com.example.samuelnyamai.leagurelore.Model;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import com.example.samuelnyamai.leagurelore.Room.ChampionsDatabase;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;
import java.util.List;

public class AllChampAsync {
    public static LiveData<List<ChampionDetails>> getchampsInstance(Context context) {
        return ChampionsDatabase.getChampionDatabseInstance(context).championsDAO().getAllChampions();
    }
    static void insertChampion(Context context, ChampionDetails champion){
        new ChampsAsync(context).execute(champion);
    }
    private static class ChampsAsync extends AsyncTask<ChampionDetails,Void ,Void> {

        @SuppressLint("StaticFieldLeak")
        private Context context;
        ChampsAsync(Context context) {
            this.context= context;
        }

        @Override
        protected Void doInBackground(ChampionDetails... championDetails) {
            ChampionsDatabase.getChampionDatabseInstance(context).championsDAO().addChampion(championDetails[0]);
            return  null;
        }
    }

}
