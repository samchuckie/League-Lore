package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.samuelnyamai.leagurelore.ViewModel.SummonerViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText email_tv,password_tv;
    Button proceed_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        email_tv = findViewById(R.id.email_tv);
        password_tv = findViewById(R.id.password_tv);
        proceed_button = findViewById(R.id.proceed_button);
        SummonerViewModel viewModel = ViewModelProviders.of(this).get(SummonerViewModel.class);
        EditText summoner_username = findViewById(R.id.summoner_username);
        final Spinner serverSpinner = findViewById(R.id.server_spinner);
        final ArrayAdapter<CharSequence> serverArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.server, android.R.layout.simple_spinner_item);
        serverArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serverSpinner.setAdapter(serverArrayAdapter);

        proceed_button.setOnClickListener(listener ->{
            String email =email_tv.getText().toString();
            String password =password_tv.getText().toString();
            // So I discovered that calling the getSelectedItem actually calls the onItemSelected ->parent.getSelectedItem(position).toString();

            String summoner_name = summoner_username.getText().toString();
            String server = serverSpinner.getSelectedItem().toString();
            if (!summoner_name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                ProgressBar login_progress = findViewById(R.id.login_progress);
                login_progress.setVisibility(View.VISIBLE);
                viewModel.getDetails(server, summoner_name);
                viewModel.getSummonerLiveData().observe(this, summoneresponse -> {
                    if (summoneresponse != null) {
                        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString(getString(R.string.summoner_name_key), summoneresponse.getName());
//                        editor.putString(getString(R.string.summoner_server), server);
//                        editor.putInt(getString(R.string.summoner_icon_key), summoneresponse.getProfileIconId());
//                        editor.putInt(getString(R.string.summoner_level_key), summoneresponse.getSummonerLevel());
                        editor.putString(getString(R.string.summoner_name_key), "sandstorm73");
                        editor.putString(getString(R.string.summoner_server), "EUW");
                        editor.putInt(getString(R.string.summoner_icon_key), 3001);
                        editor.putInt(getString(R.string.summoner_level_key), 117);

                        editor.apply();
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.e("sam", "Success");
                                startActivity(new Intent(this, Champions.class));
                            }
                            else {
                                Log.e("sam", "Failure");
                            }
                        });
                    } else {
                        Toast.makeText(this, "Sorry the summoner " + summoner_name +
                                " does not exist in " + server + " server", Toast.LENGTH_LONG).show();
                    }
                });
                login_progress.setVisibility(View.GONE);
            }
            else {
                Toast.makeText(this ,"Input all the fields" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
