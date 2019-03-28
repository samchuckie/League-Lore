package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.samuelnyamai.leagurelore.ViewModel.SummonerViewModel;

public class LogIn extends AppCompatActivity {
    // TODO EXPLAIN HOW TO GET API KEY
    // TODO GIVE A LIST OF SAMPLE SUMMONERS NAMES FOR TESTING IN NA(NORTH AMERICA)
    // TODO GIVE A LIST OF SAMPLE SUMMONERS NAMES FOR TESTING IN EUW(EUROPE WEST)
    // TODO TRY AND IMPLEMENT LOCALIZATION. DIALOG PICKER TO CHANGE LANGUAGE TO SPECIFIC REGION
    // TODO IMPLIMENT CUSTOM FORWARD ARROW. IN XD AND IMPORT SVG
    // TODO IMPLEMENT CUSTOM LAYOUT RESOURCES FOR THE DOFFERENT CONTINTENTS
    // TODO SETUP TASK TO CHECK API_KEY REFRESH AND POSSIBLE AUTOREFRESH
    // TODO CHECK INTERNET CONNECTIVITY
    // TODO ADD PROGRESS BAR
    // TODO UNCOMMENT REVISIONDATE FOR USE IN UPDATE
    SharedPreferences sharedPreferences ;
    Context context;
    String shared_preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        sharedPreferences = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        shared_preference =sharedPreferences.getString(getString(R.string.summoner_name_key) , null);
        if(shared_preference!=null){
            startActivity(new Intent(this ,Champions.class));
        }
        setContentView(R.layout.activity_log_in);
        SummonerViewModel viewModel = ViewModelProviders.of(this).get(SummonerViewModel.class);
        EditText summoner_username = findViewById(R.id.summoner_username);
        final Spinner serverSpinner = findViewById(R.id.server_spinner);
        final ArrayAdapter<CharSequence> serverArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.server ,android.R.layout.simple_spinner_item);
        serverArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Button proceed = findViewById(R.id.proceed_button);
        serverSpinner.setAdapter(serverArrayAdapter);
        proceed.setOnClickListener(clicker -> {

            // So I discovered that calling the getSelectedItem actually calls the onItemSelected ->parent.getSelectedItem(position).toString();

        viewModel.getDetails(serverSpinner.getSelectedItem().toString(),summoner_username.getText().toString());
        viewModel.getSummonerLiveData().observe(this ,summoneresponse ->{
            if (summoneresponse.getName() != null)
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.summoner_name_key), summoneresponse.getName() );
                editor.putInt(getString(R.string.summoner_icon_key ),summoneresponse.getProfileIconId());
                editor.putInt(getString(R.string.summoner_level_key),summoneresponse.getSummonerLevel());
                editor.apply();
                startActivity(new Intent(this ,Champions.class));
            }
        });
    });
}
}
