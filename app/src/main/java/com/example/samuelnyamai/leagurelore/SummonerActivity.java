package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuelnyamai.leagurelore.Adapters.ChampionPLayedAdapter;
import com.example.samuelnyamai.leagurelore.Model.SpecificSummonerModel;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionsPlayedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.LeagueRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.RankedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampioMasteryInt;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.LoginInterface;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.RankedInterface;
import com.example.samuelnyamai.leagurelore.Room.ChampionsDatabase;
import com.example.samuelnyamai.leagurelore.ViewModel.Specific_SummonerViewModel;
import com.example.samuelnyamai.leagurelore.ViewModel.SummonerViewModel;
import com.example.samuelnyamai.leagurelore.data.ChampionsPlayed;
import com.example.samuelnyamai.leagurelore.data.Summoner;
import com.example.samuelnyamai.leagurelore.data.SummonerRankedInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.API_KEY;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_ICON_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_LOADINGIMAGE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.JPG_IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PNG_IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PROFILE_BASE_URL;

public class SummonerActivity extends AppCompatActivity {
    // TODO EXTRACT THE NAME AND LEVEL TO A SEPARATE FILE AND INCLUDE THEM IN BOTH LAYOUT

    TextView s_name,s_level,sr_name,fr_name,sr_points,fr_points;
    ImageView summoner_backdrop_iv, summoner_currenticon_iv,soloranked_iv,flexranked_iv;
    RecyclerView championsplayed_rv;
    Specific_SummonerViewModel viewModeler;
    ChampionPLayedAdapter championPLayedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);
        viewModeler = ViewModelProviders.of(this).get(Specific_SummonerViewModel.class);
        summoner_backdrop_iv= findViewById(R.id.summoner_backdrop_iv);
        summoner_currenticon_iv = findViewById(R.id.summoner_currenticon_iv);
        s_name = findViewById(R.id.summonername_tv);
        s_level =  findViewById(R.id.summonerlevel_tv);
        soloranked_iv = findViewById(R.id.soloranked_iv);
        sr_name = findViewById(R.id.soloranked_tv);
        sr_points = findViewById(R.id.solorankedpoint_tv);
        flexranked_iv = findViewById(R.id.flexranked_iv);
        fr_name = findViewById(R.id.flexranked_tv);
        fr_points = findViewById(R.id.flexrankedpoints_tv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        championsplayed_rv =  findViewById(R.id.championsplayed_rv);
        championsplayed_rv.setLayoutManager(layoutManager);
        championPLayedAdapter= new ChampionPLayedAdapter();
        championsplayed_rv.setAdapter(championPLayedAdapter);
        viewModeler.getSummonerLiveData().observe(this, this::updateUi);
    }

    private void updateUi(Summoner summoner) {
        s_name.setText(summoner.getName());
        s_level.setText(String.valueOf(summoner.getSummonerLevel()));
        sr_name.setText(summoner.getSummonerRankedInfoList().get(0).getLeagueName());
        sr_points.setText(String.valueOf(summoner.getSummonerRankedInfoList().get(0).getLeaguePoints()));
        fr_name.setText(summoner.getSummonerRankedInfoList().get(1).getLeagueName());
        fr_points.setText(String.valueOf(summoner.getSummonerRankedInfoList().get(1).getLeaguePoints()));
        int champid=summoner.getChampionsPlayedList().get(0).getChampionId();
        Picasso.get().load(PROFILE_BASE_URL + summoner.getProfileIconId() + PNG_IMAGE_EXTENSION).into(summoner_currenticon_iv);
        viewModeler.getBackdrop_image(this,champid).observe(this,name ->{
            Picasso.get().load(CHAMPION_LOADINGIMAGE_URL + name + "_0" + JPG_IMAGE_EXTENSION).fit().into(summoner_backdrop_iv);
        });
        List<String> champidList = new ArrayList<>();
        for (ChampionsPlayed championsPlayed : summoner.getChampionsPlayedList()) {
            champidList.add(String.valueOf(championsPlayed.getChampionId()));
        }
        viewModeler.getChampionsPlayedList(this,champidList).observe(this,observer->{
            championPLayedAdapter.setChampionsPlayedlist(observer);
        });
        getDrawable(summoner.getSummonerRankedInfoList().get(0).getTier(),soloranked_iv);
        getDrawable(summoner.getSummonerRankedInfoList().get(1).getTier(),flexranked_iv);
    }


    private void getDrawable(String tier,ImageView ranked_type) {
        switch (tier) {
            case ("IRON"):
                Picasso.get().load(R.drawable.silver).into(ranked_type);
                break;
            case ("BRONZE"):
                Picasso.get().load(R.drawable.bronze).into(ranked_type);
                break;
            case ("SILVER"):
                Picasso.get().load(R.drawable.silver).into(ranked_type);
                break;
            case ("GOLD"):
                Picasso.get().load(R.drawable.bronze).into(ranked_type);
                break;
            case ("PLATINUM "):
                Picasso.get().load(R.drawable.silver).into(ranked_type);
                break;
            case ("DIAMOND"):
                Picasso.get().load(R.drawable.bronze).into(ranked_type);
                break;
            case ("MASTER"):
                Picasso.get().load(R.drawable.silver).into(ranked_type);
                break;
            case ("GRANDMASTER"):
                Picasso.get().load(R.drawable.bronze).into(ranked_type);
                break;
            case ("CHALLENGER"):
                Picasso.get().load(R.drawable.silver).into(ranked_type);
                break;
        }
    }
}

