package com.example.samuelnyamai.leagurelore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.samuelnyamai.leagurelore.Fragments.ChampionFragment;
import com.squareup.picasso.Picasso;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PNG_IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PROFILE_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.SERVER_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.USERNAME_EXTRA;

public class Champions extends AppCompatActivity {
    // TODO CAN DATABINDING BE USED WITH NAV_DRAWER
    // TODO BACK ACTIVITY SHOULD EXIT THE APP NOT GO TO LOG IN
    // TODO PREFERENCE FRAGMENT FOR LANGUAGE
    //  TODO ADD CHALLENGER TIER FOR THE NAVIGATION OPTIONS

    SharedPreferences sharedPreferences;
    NavigationView league_navigationview;
    DrawerLayout drawerLayout;
    String username_pref,server_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawerLayout);
        sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        league_navigationview = findViewById(R.id.league_nav_view);
        TextView username_tv = league_navigationview.getHeaderView(0).findViewById(R.id.username);
        TextView level_tv = league_navigationview.getHeaderView(0).findViewById(R.id.level);
        ImageView usericon_iv = league_navigationview.getHeaderView(0).findViewById(R.id.user_icon);
        if (sharedPreferences.contains(getString(R.string.summoner_name_key))) {
            username_pref = sharedPreferences.getString(getString(R.string.summoner_name_key), null);
            server_pref = sharedPreferences.getString(getString(R.string.summoner_server),null);
            int user_level = sharedPreferences.getInt(getString(R.string.summoner_level_key), 0);
            int user_icon = sharedPreferences.getInt(getString(R.string.summoner_icon_key), 0);
            username_tv.setText(username_pref);
            level_tv.setText(String.valueOf(user_level));
            Picasso.get().load(PROFILE_BASE_URL + user_icon + PNG_IMAGE_EXTENSION).noFade().into(usericon_iv);
        }
        ChampionFragment championFragment = new ChampionFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.champion_framelayout ,championFragment).commit();
        usericon_iv.setOnClickListener(view -> {
            Intent intent = new Intent(this , SummonerActivity.class);
            intent.putExtra(USERNAME_EXTRA ,username_pref);
            intent.putExtra(SERVER_EXTRA ,server_pref);
            startActivity((intent));
        });
        league_navigationview.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.logout:
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.summoner_name_key), null);
                    editor.apply();
                    startActivity(new Intent(this ,LogIn.class));

            }
            drawerLayout.closeDrawers();
            return  true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.summoner_search:

        }
        return super.onOptionsItemSelected(item);
    }





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.league_menu, menu);
//        return  true;
//
//    }
}
