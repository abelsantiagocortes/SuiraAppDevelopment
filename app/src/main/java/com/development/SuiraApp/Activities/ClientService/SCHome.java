package com.development.SuiraApp.Activities.ClientService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.development.SuiraApp.Fragments.ClientService.Faq_tab;
import com.development.SuiraApp.R;
import com.development.SuiraApp.Fragments.ClientService.Suggestions_tab;
import com.development.SuiraApp.Adapters.TabViewPagerAdapter;
import com.development.SuiraApp.Fragments.ClientService.Terms_tab;
import com.google.android.material.tabs.TabLayout;

public class SCHome extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schome);

        tabLayout=(TabLayout) findViewById(R.id.tabLayoutSC);
        viewPager=(ViewPager) findViewById(R.id.viewPagerSC);

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
