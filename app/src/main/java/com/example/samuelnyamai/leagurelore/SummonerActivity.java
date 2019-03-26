package com.example.samuelnyamai.leagurelore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.samuelnyamai.leagurelore.Model.Specific_ChampionModel;

public class SummonerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);
        Specific_ChampionModel specific_championModel = new Specific_ChampionModel();
    }
}

