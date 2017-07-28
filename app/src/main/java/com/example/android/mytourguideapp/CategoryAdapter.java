package com.example.android.mytourguideapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Admin on 7/27/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context context;

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return context.getString(R.string.category_attractions);

        }else if (position == 1){
            return context.getString(R.string.category_food_spots);

        } else if (position == 2){
           return context.getString(R.string.category_pubs);
        }
        else{
            return context.getString(R.string.category_accommodation);
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return  new AttractionsFragment();

        }else if (position == 1){
            return new FoodSpotsFragment();
        }
        else if (position == 2){
            return new PubsFragment();
        }
        else{
            return new AccommodateFragment();
        }
    }

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
}
