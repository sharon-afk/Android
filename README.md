// ===========================
// File: MainActivity.java
// ===========================
package com.example.intends;

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
    }
}

// ===========================
// File: IntendS2.java
// ===========================
package com.example.intends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class IntendS2 extends AppCompatActivity {

    TextView fullname_result, emailid_result, password_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intend_s2);

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

// ===========================
// File: res/layout/activity_main.xml
// ===========================
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/main_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="16dp">

    <EditText
        android:id="@+id/fullname"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Full Name"
        android:layout_marginTop="10dp" />

    <EditText
        android:id="@+id/emailid"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:layout_marginTop="10dp"
        android:inputType="textEmailAddress" />

    <EditText
        android:id="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Password"
        android:layout_marginTop="10dp"
        android:inputType="textPassword" />

    <Button
        android:id="@+id/register_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Register"
        android:layout_marginTop="20dp" />

</LinearLayout>

// ===========================
// File: res/layout/activity_intend_s2.xml
// ===========================
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="10dp">

    <TextView
        android:id="@+id/fullname_result"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp" />

    <TextView
        android:id="@+id/emailid_result"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp" />

    <TextView
        android:id="@+id/password_result"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp" />

</LinearLayout>
