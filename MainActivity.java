package com.example.regathil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    EditText fullname, emailid, password;
    Button register_btn;
    LinearLayout main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullname = findViewById(R.id.fullname);
        emailid = findViewById(R.id.emailid);
        password = findViewById(R.id.password);
        register_btn = findViewById(R.id.register_btn);
        main_layout = findViewById(R.id.main_layout);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullname.setError(null);
                emailid.setError(null);
                password.setError(null);

                String password_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                String fullname_text = fullname.getText().toString();
                String emailid_text = emailid.getText().toString();
                String password_text = password.getText().toString();

                if (fullname_text.equals("")) {
                    fullname.requestFocus();
                    fullname.setError("Please enter fullname!!");
                } else if (emailid_text.equals("")) {
                    emailid.requestFocus();
                    emailid.setError("Please enter email-id!!");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailid_text).matches()) {
                    emailid.requestFocus();
                    emailid.setError("Please enter a valid email-id!!");
                } else if (!password_text.matches(password_regex)) {
                    password.requestFocus();
                    password.setError("Password should contain \n" +
                            "a digit must occur at least once \n" +
                            "a lower case letter must occur at least once \n" +
                            "an upper case letter occur at least once \n" +
                            "a special character like @#$%^&+= \n" +
                            "No blank spaces allowed \n" +
                            "at least 6 characters");
                } else {
                    SharedPreferences pref = getSharedPreferences("register_data", MODE_PRIVATE);
                    SharedPreferences.Editor pref_edit = pref.edit();
                    pref_edit.putString("reg_fullname", fullname_text);
                    pref_edit.putString("reg_emailid", emailid_text);
                    pref_edit.putString("reg_password", password_text);
                    pref_edit.apply();

                    Intent intent = new Intent(getApplicationContext(), IntendS2.class);
                    startActivity(intent);
                }
            }
        });
    }}
