package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

       private EditText eName;
       private EditText ePassword;
       private Button eLogin;

       private FirebaseAuth auth;


    boolean isValid = false;
       private  int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(SaveSharedPreference.getUserName(MainActivity.this).length() == 0) {
            // call Login Activity

            setContentView(R.layout.activity_login_page);

            eName = findViewById(R.id.etName);
            ePassword = findViewById(R.id.etPassword);
            eLogin = findViewById(R.id.BtnLogin);

            auth = FirebaseAuth.getInstance();

            eLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {

                    String inputName = eName.getText().toString();
                    String inputPassword = ePassword.getText().toString();
                    loginUser(inputName, inputPassword);


                }

                private void loginUser (final String Name, String Password) {

                    auth.signInWithEmailAndPassword(Name, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess (AuthResult authResult) {
                            SaveSharedPreference.setUserName(MainActivity.this,Name);
                            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, HomePage.class));
                            finish();
                        }
                    });
                }


            });
        }
        else
    {
        // Stay at the current activity.
       // setContentView(R.layout.fragment_home);
        startActivity(new Intent(MainActivity.this, HomePage.class));
    }
    }


}