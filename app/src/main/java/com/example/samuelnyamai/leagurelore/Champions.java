package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuelnyamai.leagurelore.Fragments.ChampionFragment;
import com.example.samuelnyamai.leagurelore.ViewModel.SummonerViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PNG_IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PROFILE_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.SERVER_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.USERNAME_EXTRA;

public class Champions extends AppCompatActivity {
    // TODO ADD SHARED ANIMATIONS
    // TODO CAN DATABINDING BE USED WITH NAV_DRAWER
    // TODO BACK ACTIVITY SHOULD EXIT THE APP NOT GO TO LOG IN
    // TODO PREFERENCE FRAGMENT FOR LANGUAGE
    //  TODO ADD CHALLENGER TIER FOR THE NAVIGATION OPTIONS
    SummonerViewModel summonerViewModel;
    SharedPreferences sharedPreferences;
    NavigationView league_navigationview;
    DrawerLayout drawerLayout;
    String username_pref,server_pref;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
//        MobileAds.initialize(this, "ca-app-pub-1271995389221448~1120532171");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        summonerViewModel = ViewModelProviders.of(this).get(SummonerViewModel.class);
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
        TextView server_tv = league_navigationview.getHeaderView(0).findViewById(R.id.server_tv);
        ImageView usericon_iv = league_navigationview.getHeaderView(0).findViewById(R.id.user_icon);
        if (sharedPreferences.contains(getString(R.string.summoner_name_key))) {
            username_pref = sharedPreferences.getString(getString(R.string.summoner_name_key), null);
            server_pref = sharedPreferences.getString(getString(R.string.summoner_server),null);
            int user_level = sharedPreferences.getInt(getString(R.string.summoner_level_key), 0);
            int user_icon = sharedPreferences.getInt(getString(R.string.summoner_icon_key), 0);
            username_tv.setText(username_pref);
            server_tv.setText(server_pref);
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
                    FirebaseAuth.getInstance().signOut();
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.league_menu, menu);
        return  true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem summoner_search =  menu.findItem(R.id.summoner_search);
        SearchView searchView = (SearchView) summoner_search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


//            So here is a list of names that can be searched according to region(THE SPINNER CHOICES).
//            NA - (SEARCH FOR "NA ranked ladder" IN GOOGLE AND CHOOSE FIRST RESULT) Examples of names include - Shiphtur, Sophist Sage1.
//            EUW- (SEARCH FOR "EUW ranked ladder" IN GOOGLE AND CHOOSE FIRST RESULT) Examples of names include - sandstorm73 ,charliesdemon.
//            BZL- (SEARCH FOR "BRAZIL ranked ladder" IN GOOGLE AND CHOOSE FIRST RESULT) Examples of names include - PIJACK.
//            JPN- (SEARCH FOR "JPN ranked ladder" IN GOOGLE AND CHOOSE FIRST RESULT) Examples of names include - isurugi .
//            OCE- (SEARCH FOR "OCE ranked ladder" IN GOOGLE AND CHOOSE FIRST RESULT) Examples of names include - PIuviophile, alukaa.

                summonerViewModel.getDetails("EUW",query);
                summonerViewModel.getSummonerLiveData().observe(Champions.this ,summoneresponse -> {
                    if (summoneresponse != null) {
                        Intent intent = new Intent(Champions.this, SummonerActivity.class);
                        intent.putExtra(USERNAME_EXTRA ,summoneresponse.getName());
                        intent.putExtra(SERVER_EXTRA ,server_pref);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Champions.this, "Sorry the summoner " + query +
                                " does not exist in " + server_pref + " server", Toast.LENGTH_LONG).show();
                    }

                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("sam" , "The changed text is " + newText);
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
