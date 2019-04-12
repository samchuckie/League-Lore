package com.example.samuelnyamai.leagurelore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


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
            if(!email.isEmpty() && !password.isEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, Champions.class));
                    } else {
                        Log.e("sam", "Log in failed");
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
}
