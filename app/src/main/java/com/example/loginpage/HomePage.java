package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.loginpage.Fragment.AnnouncementFragment;
import com.example.loginpage.Fragment.AttendanceFragment;
import com.example.loginpage.Fragment.HomeFragment;
import com.example.loginpage.Fragment.LogoutFragment;
import com.example.loginpage.Fragment.MyCourseFragment;
import com.example.loginpage.Fragment.ProfileFragment;
import com.example.loginpage.Fragment.TimeTableFragment;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        toolbar = findViewById(R.id.homeAppBar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showFragment(new HomeFragment());

    }

    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            showFragment(new HomeFragment());
        }

        if (id == R.id.timetable) {
            showFragment(new TimeTableFragment());
        }


        if (id == R.id.mycourse) {
            showFragment(new MyCourseFragment());
        }


        if (id == R.id.attendance) {
            showFragment(new AttendanceFragment());
        }


        if (id == R.id.announcement) {
            showFragment(new AnnouncementFragment());
        }


        if (id == R.id.profile) {
            showFragment(new ProfileFragment());
        }

        else if (id == R.id.logout) {
           Intent intent = new Intent(getApplicationContext(),MainActivity.class);
           startActivity(intent);
           finish();
        }
      mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment (Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout,fragment);
        ft.commit();
    }
     public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
     }


}