package com.example.regathil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class IntendS2 extends AppCompatActivity {

    TextView fullname_result, emailid_result, password_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intends2);

        fullname_result = findViewById(R.id.fullname_result);
        emailid_result = findViewById(R.id.emailid_result);
        password_result = findViewById(R.id.password_result);

        SharedPreferences pref = getSharedPreferences("register_data", MODE_PRIVATE);
        String name = pref.getString("reg_fullname", "Not Available!!");
        String email = pref.getString("reg_emailid", "Not Available!!");
        String password = pref.getString("reg_password", "Not Available!!");

        fullname_result.setText(name);
        emailid_result.setText(email);
        password_result.setText(password);
    }
}
