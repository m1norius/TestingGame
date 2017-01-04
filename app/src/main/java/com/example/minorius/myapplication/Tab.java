package com.example.minorius.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by minorius on 03.01.2017.
 */

public class Tab extends AppCompatActivity {

    public TabLayout tableLayout;
    public ViewPager viewPager;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_la);

        tableLayout = (TabLayout) findViewById(R.id.tab_la);
        viewPager = (ViewPager) findViewById(R.id.view_id);


        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));
        tableLayout.setupWithViewPager(viewPager);

        tableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

    }

    private class CustomAdapter extends FragmentPagerAdapter {

        String fragments [] = {"Тварини", "Транспорт"};
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Fragment1.media_fragment != null){
            Fragment1.media_fragment.stop();
        }
        if (Fragment2.media_fragment != null){
            Fragment2.media_fragment.stop();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(Fragment1.media_fragment != null){
            Fragment1.media_fragment.stop();
        }
        if (Fragment2.media_fragment != null){
            Fragment2.media_fragment.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Fragment1.media_fragment != null){
            Fragment1.media_fragment.stop();
        }
        if (Fragment2.media_fragment != null){
            Fragment2.media_fragment.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(Fragment1.media_fragment != null){
            Fragment1.media_fragment.stop();
        }
        if (Fragment2.media_fragment != null){
            Fragment2.media_fragment.stop();
        }
    }
}
