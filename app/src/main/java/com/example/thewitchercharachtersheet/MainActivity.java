package com.example.thewitchercharachtersheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.thewitchercharachtersheet.fragments.CreationFragment;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    HashMap<String, CreationFragment> fragments;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState==null)
        {
            navigationView.setCheckedItem(R.id.nav_main_char);
        }
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        //toolbar.setNavigationIcon(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                return false;
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void ThrowDice(View view) {
        Random random = new Random();
        int diceRoll = random.nextInt(11);
        Snackbar snackbar = Snackbar.make(view, "Результат броска: " + diceRoll, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}