package com.example.samuelnyamai.leagurelore;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuelnyamai.leagurelore.Fragments.ChampionFragment;
import com.squareup.picasso.Picasso;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PROFILE_BASE_URL;

public class Champions extends AppCompatActivity {
    // TODO CAN DATABINDING BE USED WITH NAV_DRAWER
    // TODO BACK ACTIVITY SHOULD EXIT THE APP NOT GO TO LOG IN
    // TODO PREFERENCE FRAGMENT FOR LANGUAGE

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        NavigationView league_navigationview = findViewById(R.id.league_nav_view);
        TextView username_tv = league_navigationview.getHeaderView(0).findViewById(R.id.username);
        TextView level_tv = league_navigationview.getHeaderView(0).findViewById(R.id.level);
        ImageView usericon_iv = league_navigationview.getHeaderView(0).findViewById(R.id.user_icon);
        if (sharedPreferences.contains(getString(R.string.summoner_name_key))) {
            String username_pref = sharedPreferences.getString(getString(R.string.summoner_name_key), null);
            int user_level = sharedPreferences.getInt(getString(R.string.summoner_level_key), 0);
            int user_icon = sharedPreferences.getInt(getString(R.string.summoner_icon_key), 0);
            username_tv.setText(username_pref);
            level_tv.setText(String.valueOf(user_level));
            StringBuilder path = new StringBuilder(PROFILE_BASE_URL).append(user_icon).append(IMAGE_EXTENSION);
            Picasso.get().load(path.toString()).noFade().into(usericon_iv);
        }
        ChampionFragment championFragment = new ChampionFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.champion_framelayout ,championFragment).commit();
    }
}
