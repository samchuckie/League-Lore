package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.samuelnyamai.leagurelore.Adapters.ChampionPLayedAdapter;
import com.example.samuelnyamai.leagurelore.ViewModel.Specific_SummonerViewModel;
import com.example.samuelnyamai.leagurelore.data.ChampionsPlayed;
import com.example.samuelnyamai.leagurelore.data.Summoner;
import com.example.samuelnyamai.leagurelore.data.SummonerRankedInfo;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_LOADINGIMAGE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.JPG_IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.LOCAL_URL_IMAGESPLASH;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.LOCAL_URL_PROFILE;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PNG_IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PROFILE_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.SERVER_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.USERNAME_EXTRA;

public class SummonerActivity extends AppCompatActivity {
    // TODO EXTRACT THE NAME AND LEVEL TO A SEPARATE FILE AND INCLUDE THEM IN BOTH LAYOUT
    // TODO ADD A FAB FOR HISTORY
    // TODO IMPLIMENT INTERFACE FOR ERROR

    TextView s_name,s_level,sr_name,fr_name,sr_points,fr_points ,leaguepointssolo_tv ,leaguepointsflex_tv;
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
        leaguepointssolo_tv = findViewById(R.id.leaguepointssolo_tv);
        leaguepointsflex_tv = findViewById(R.id.leaguepointsflex_tv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        championsplayed_rv =  findViewById(R.id.championsplayed_rv);
        championsplayed_rv.setLayoutManager(layoutManager);
        championPLayedAdapter= new ChampionPLayedAdapter();
        championsplayed_rv.setAdapter(championPLayedAdapter);
        Intent intent = getIntent();
        if (intent.hasExtra(USERNAME_EXTRA) && intent.hasExtra(SERVER_EXTRA)){
            String username = intent.getStringExtra(USERNAME_EXTRA);
            String server = intent.getStringExtra(SERVER_EXTRA);
            viewModeler.getData(username ,server);
            viewModeler.getSummonerLiveData().observe(this, summoner ->{
                Log.e("sam","The summoner is " + summoner);
                ProgressBar summoner_progress = findViewById(R.id.summoner_progress);
                summoner_progress.setVisibility(View.GONE);
                if(summoner!=null) {
                    FrameLayout summoner_frame = findViewById(R.id.summoner_frame);
                    Log.e("sam","Summoner is not null at all");
                    summoner_frame.setVisibility(View.VISIBLE);
                    updateUi(summoner);
                }
                else{
                    Toast.makeText(this ,"Sorry ,summoner not found" ,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateUi(Summoner summoner) {
        s_name.setText(summoner.getName());
        s_level.setText(String.valueOf(summoner.getSummonerLevel()));
        int champid=summoner.getChampionsPlayedList().get(0).getChampionId();
        Picasso.get().load(PROFILE_BASE_URL + summoner.getProfileIconId() + PNG_IMAGE_EXTENSION).into(summoner_currenticon_iv);
        Picasso.get().load(CHAMPION_LOADINGIMAGE_URL + "Ahri" + "_0" + JPG_IMAGE_EXTENSION).fit().into(summoner_backdrop_iv);

        //retreaving from database
//        viewModeler.getBackdrop_image(this,champid).observe(this,name ->
////                Picasso.get().load(CHAMPION_LOADINGIMAGE_URL + name + "_0" + JPG_IMAGE_EXTENSION).fit().into(summoner_backdrop_iv)
//        );
        List<String> champidList = new ArrayList<>();
        for (ChampionsPlayed championsPlayed : summoner.getChampionsPlayedList()) {
            champidList.add(String.valueOf(championsPlayed.getChampionId()));
        }
        //setting champions played to adapter
        viewModeler.getChampionsPlayedList(this,champidList).observe(this,observer-> championPLayedAdapter.setChampionsPlayedlist(observer));
        if(summoner.getSummonerRankedInfoList().size()==0){
            LinearLayout unranked = findViewById(R.id.unranked);
            unranked.setVisibility(View.VISIBLE);
        }
        for(SummonerRankedInfo summonerRankedInfo:summoner.getSummonerRankedInfoList()){
            if(summonerRankedInfo.getQueueType().equals("RANKED_FLEX_SR")){
                flexUI(summonerRankedInfo);
            }
            else {
                rankedUI(summonerRankedInfo);
            }
        }
    }
    private void rankedUI(SummonerRankedInfo summonerRankedInfo) {
        LinearLayout rankedsolo_linear = findViewById(R.id.rankedsolo_linear);
        rankedsolo_linear.setVisibility(View.VISIBLE);
        String solo = summonerRankedInfo.getTier();
        String solo_points = solo + " " + summonerRankedInfo.getRank() ;
        sr_name.setText(summonerRankedInfo.getLeagueName());
        String all = summonerRankedInfo.getLeaguePoints()+ " LP" + "  /"+ summonerRankedInfo.getWins() + "W " +
                summonerRankedInfo.getLosses() + "L";
        sr_points.setText(solo_points);
        leaguepointssolo_tv.setText(all);
        getDrawable(solo,soloranked_iv);

    }
    private void flexUI(SummonerRankedInfo summonerRankedInfo) {
        LinearLayout rankedflex_linear = findViewById(R.id.rankedflex_linear);
        rankedflex_linear.setVisibility(View.VISIBLE);
        String flex = summonerRankedInfo.getTier();
        String flex_points = flex + " " + summonerRankedInfo.getRank() ;
        fr_name.setText(summonerRankedInfo.getLeagueName());
        fr_points.setText(flex_points);
        String all = summonerRankedInfo.getLeaguePoints()+ " LP" + "  /" + summonerRankedInfo.getWins() + "W " +
                        summonerRankedInfo.getLosses() + "L";
        leaguepointsflex_tv.setText(all);
        getDrawable(flex,flexranked_iv);
    }

    private void getDrawable(String tier,ImageView ranked_type) {
        switch (tier) {
            case ("IRON"):
                Picasso.get().load(R.drawable.iron).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("BRONZE"):
                Picasso.get().load(R.drawable.bronze).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("SILVER"):
                Picasso.get().load(R.drawable.silver).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("GOLD"):
                Picasso.get().load(R.drawable.gold).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("PLATINUM "):
                Picasso.get().load(R.drawable.platinum).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("DIAMOND"):
                Picasso.get().load(R.drawable.diamond).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("MASTER"):
                Picasso.get().load(R.drawable.master).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("GRANDMASTER"):
                Picasso.get().load(R.drawable.grandmaster).placeholder(R.drawable.unranked).into(ranked_type);
                break;
            case ("CHALLENGER"):
                Picasso.get().load(R.drawable.challenger).placeholder(R.drawable.unranked).into(ranked_type);
                break;
        }
    }
}

