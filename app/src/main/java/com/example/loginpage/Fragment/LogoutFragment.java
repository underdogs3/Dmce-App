package com.example.loginpage.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginpage.R;

public class LogoutFragment extends Fragment {


    public LogoutFragment () {
        // Required empty public constructor
    }



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login_page, container, false);
    }
}