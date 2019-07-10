package com.example.samuelnyamai.leagurelore;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.samuelnyamai.leagurelore.Model.Checker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.NETWORK;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.UNREGISTERED;


public class LogIn extends AppCompatActivity {
    // TODO TRY AND IMPLEMENT LOCALIZATION. DIALOG PICKER TO CHANGE LANGUAGE TO SPECIFIC REGION
    // TODO IMPLIMENT CUSTOM FORWARD ARROW. IN XD AND IMPORT SVG
    // TODO IMPLEMENT CUSTOM LAYOUT RESOURCES FOR THE DOFFERENT CONTINTENTS
    // TODO SETUP TASK TO CHECK API_KEY REFRESH AND POSSIBLE AUTOREFRESH
    // TODO UNCOMMENT REVISIONDATE FOR USE IN UPDATE

    Button login_proceed ,login_signup;
    TextView login_email, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, Champions.class));
            Log.e("sam", "auth is not null");
        }
        setContentView(R.layout.activity_log_in);
        login_proceed = findViewById(R.id.login_proceed);
        login_signup = findViewById(R.id.login_signup);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        login_proceed.setOnClickListener(listener -> {
            String email = login_email.getText().toString();
            String password = login_password.getText().toString();
            if(Checker.checkEmail(email) && Checker.checkPassword(password)) {


                // DOING THIS MANUALLY AS IT REQUIRES SOMEONE TO KNOW SPECICIFIC DETAILS




                SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.summoner_name_key), "sandstorm73");
                editor.putString(getString(R.string.summoner_server), "EUW");
                editor.putInt(getString(R.string.summoner_icon_key), 3001);
                editor.putInt(getString(R.string.summoner_level_key), 117);
                editor.apply();
                startActivity(new Intent(this, Champions.class));

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, Champions.class));
                    } else {
                        String exception = Objects.requireNonNull(task.getException()).getMessage();
                        if (exception.equals(NETWORK)){
                            Log.e("sam", "Network issues");
                            networkToast();
                        }
                        if(exception.equals(UNREGISTERED)){
                            Log.e("sam", "Sorry unregistered user");
                            userMissingToast();
                        }
                    }
                });
            }
            else {
                Toast.makeText(this ,"Input all the fields" ,Toast.LENGTH_SHORT).show();
            }
        });
        login_signup.setOnClickListener(listener -> {
            startActivity(new Intent(this, SignUp.class));
        });

    }
    private void networkToast() {
        LayoutInflater inflater = getLayoutInflater();
        View network_toast = inflater.inflate(R.layout.networktoast, findViewById(R.id.network_toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(network_toast);
        toast.show();
    }
    private void userMissingToast() {
        LayoutInflater inflater = getLayoutInflater();
        View network_toast = inflater.inflate(R.layout.usermissingtoast, findViewById(R.id.user_missing_toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(network_toast);
        toast.show();
    }
}
