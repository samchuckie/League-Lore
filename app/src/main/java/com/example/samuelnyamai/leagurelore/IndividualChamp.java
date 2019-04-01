package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.TransitionManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.samuelnyamai.leagurelore.Fragments.AbilitiesFragment;
import com.example.samuelnyamai.leagurelore.Fragments.LoreFragment;
import com.example.samuelnyamai.leagurelore.ViewModel.AllChampionsViewModel;

public class IndividualChamp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_champ);

        LoreFragment loreFragment= new LoreFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.individual_frame ,loreFragment).commit();

        BottomNavigationView bottom_nav =  findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.e("sam" , "Selected item is " + bottom_nav.getSelectedItemId());
                Log.e("sam" , "Selected item is " + menuItem.getTitle());
                AbilitiesFragment abilitiesFragment= new AbilitiesFragment();
                fragmentManager.beginTransaction().replace(R.id.individual_frame ,abilitiesFragment).commit();
                return true;
            }
        });
    }
}

