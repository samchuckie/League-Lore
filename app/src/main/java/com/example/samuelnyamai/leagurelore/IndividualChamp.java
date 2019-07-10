package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.samuelnyamai.leagurelore.Fragments.AbilitiesFragment;
import com.example.samuelnyamai.leagurelore.Fragments.LoreFragment;
import com.example.samuelnyamai.leagurelore.ViewModel.IndividualViewModel;
import com.squareup.picasso.Picasso;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_ICON_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_LOADINGIMAGE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.ID_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.IMAGE_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.JPG_IMAGE_EXTENSION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.KEY_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.LOCAL_URL_CHAMPION;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.LOCAL_URL_IMAGESPLASH;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.NAME_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.TITLE_EXTRA;

public class IndividualChamp extends AppCompatActivity {
    IndividualViewModel individualViewModel;
    ImageView summoner_backdrop_iv ,summoner_currenticon_iv;
    TextView summonername_tv ,level_tv, summonerlevel_tv;
    String title,key,name ,id,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_champ);
        individualViewModel = ViewModelProviders.of(this).get(IndividualViewModel.class);
        individualViewModel.getVisible().observe(this , visiblestate ->{
            if(visiblestate!=null && visiblestate.equals("visible")){
                ProgressBar progress_bar =  findViewById(R.id.progress_bar);
                CardView carder = findViewById(R.id.carder);
                progress_bar.setVisibility(View.GONE);
                carder.setVisibility(View.VISIBLE);
            }
        });

        summoner_backdrop_iv = findViewById(R.id.summoner_backdrop_iv);
        summoner_currenticon_iv = findViewById(R.id.summoner_currenticon_iv);
        summonername_tv =findViewById(R.id.summonername_tv);
        summonerlevel_tv =findViewById(R.id.summonerlevel_tv);
        level_tv =findViewById(R.id.level_tv);
        level_tv.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(KEY_EXTRA )&& intent.hasExtra(NAME_EXTRA) && intent.hasExtra(TITLE_EXTRA)) {
            key = intent.getStringExtra(KEY_EXTRA);
            name = intent.getStringExtra(NAME_EXTRA);
            title = intent.getStringExtra(TITLE_EXTRA);
            id = intent.getStringExtra(ID_EXTRA);
            image = intent.getStringExtra(IMAGE_EXTRA);
            individualViewModel.setKey(key);
            individualViewModel.setName(id);
            updateUI();
        }
        LoreFragment loreFragment = new LoreFragment();
        AbilitiesFragment abilitiesFragment = new AbilitiesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (individualViewModel.getSavedstate()==null){
            fragmentManager.beginTransaction().add(R.id.individual_frame, loreFragment).commit();
        }
        else {
            switch(individualViewModel.getSavedstate()){
                case "lore":
                    fragmentManager.beginTransaction().replace(R.id.individual_frame, loreFragment).commit();
                    break;
                case "abilities":
                    fragmentManager.beginTransaction().replace(R.id.individual_frame, abilitiesFragment).commit();
                    break;

            }
        }

        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        fragmentManager.beginTransaction().replace(R.id.individual_frame, loreFragment).commit();
                        break;
                    case R.id.abilities:
                        fragmentManager.beginTransaction().replace(R.id.individual_frame, abilitiesFragment).commit();
                        break;
                }
                return true;
            }
        });
    }

    private void updateUI() {
        Picasso.get().load(CHAMPION_LOADINGIMAGE_URL + id + "_0" + JPG_IMAGE_EXTENSION).fit().into(summoner_backdrop_iv);
        Picasso.get().load(CHAMPION_ICON_BASE_URL + image).into(summoner_currenticon_iv);
        summonername_tv.setText(name);
        summonerlevel_tv.setText(title);
    }
}


