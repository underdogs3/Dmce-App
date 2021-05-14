package com.example.loginpage.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginpage.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

Button resetTextLink;
FirebaseAuth fAuth;

    public LogoutFragment () {
        // Required empty public constructor
    }



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    resetTextLink = resetTextLink.findViewById(R.id.resetLink);

    resetTextLink.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v) {

        }
    });
        return inflater.inflate(R.layout.activity_login_page, container, false);
    }

}