package com.example.android.mytourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(),this);

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_admin_menu:
                Intent intentViews = new Intent(this, ViewsTourDataActivity.class);
                startActivity(intentViews);
                return true;
            case R.id.eat_admin_menu:
                Intent intentEat = new Intent(this,EatTourDataActivity.class);
                startActivity(intentEat);
                return true;
            case R.id.drink_admin_menu:
                Intent intentDrink  = new Intent(this,DrinkTourDataActivity.class);
                startActivity(intentDrink);
                return true;
            case R.id.stay_admin_menu:
                Intent intentStay = new Intent(this,StayTourDataActivity.class);
                startActivity(intentStay);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
