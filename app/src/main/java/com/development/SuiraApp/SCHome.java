package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SCHome extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schome);
        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);

        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager) findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);
        setUpViewPager(viewPager);
    }

    public void setUpViewPager(ViewPager viewPager){

        TabViewPagerAdapter tabViewPagerAdapter= new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.addFragment(new Faq_tab(),"FAQ");
        tabViewPagerAdapter.addFragment(new Suggestions_tab(),"Suggestions");
        tabViewPagerAdapter.addFragment(new Terms_tab(),"Terms & Conditions");
        viewPager.setAdapter(tabViewPagerAdapter);

    }

}
