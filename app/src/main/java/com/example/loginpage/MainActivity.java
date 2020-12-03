package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

       private EditText eName;
       private EditText ePassword;
       private Button eLogin;


    boolean isValid = false;
       private  int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.BtnLogin);


        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){

                    Toast.makeText(MainActivity.this, "Please enter all the details correctly",Toast.LENGTH_SHORT).show();
                }else{

                    isValid = validate(inputName, inputPassword);

                    if(!isValid){

                        counter--;
                        Toast.makeText(MainActivity.this, "Incorrect credentials entered",Toast.LENGTH_SHORT).show();

                        Toast.makeText(MainActivity.this, "No. of attempts Remaining:" + counter ,Toast.LENGTH_SHORT).show();


                        if(counter==0){
                            eLogin.setEnabled(false);
                        }

                    }else {
                        Toast.makeText(MainActivity.this, "Login successful",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }

    private boolean validate (String name, String password){

        String password1 = "12345678";
        String username = "Admin";
        return name.equals(username) && password.equals(password1);
    }
}