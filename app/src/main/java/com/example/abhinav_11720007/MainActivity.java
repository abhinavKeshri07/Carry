package com.example.abhinav_11720007;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abhinav_11720007.fragments.carry;
import com.example.abhinav_11720007.fragments.carryOthers.GroupNameDialogue;
import com.example.abhinav_11720007.fragments.goal;
import com.example.abhinav_11720007.fragments.task;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GroupNameDialogue.GroupNameDialogueListener {
private DrawerLayout drawer;
private String NewGroupName;
private carry c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.tasks);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new task()).commit();
        }
    }
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.carryItems:
                c = new carry();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,c).commit();
                c.addMainActivity(this);
                break;
            case R.id.goals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new goal()).commit();
                break;
            case R.id.tasks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new task()).commit();
                break;
            case R.id.reportBug:
                Toast.makeText(this, "report",Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void applyText(String s){
        this.NewGroupName = s;
        c.setNewGroupName(s);
    }
    public void setcarry(carry c){this.c = c;}
}
