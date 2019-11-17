package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.development.SuiraApp.Activities.Home_Tab;
import com.development.SuiraApp.Activities.Oppor_tab;
import com.development.SuiraApp.Activities.Profile_tab;
import com.google.android.material.tabs.TabLayout;

public class HomeUserClient extends AppCompatActivity {


    //Se definen el tab(donde estarán todas las posibilidades de tab) Inferior
    //Se define el viewPager que es el que muestra lo que tiene cada actividad por aparte
    TabLayout tabLayouts;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user_client);
        //Se infla tool bar para el hamburguesa
        Toolbar toolbar = findViewById(R.id.toolbarMenus);
        setSupportActionBar(toolbar);

        //Se inflan tab y view a modificar
        tabLayouts=(TabLayout) findViewById(R.id.tabLayoutHome);
        viewPager=(ViewPager) findViewById(R.id.viewPagerHome);


        //Le mete el viewpager al tablayout
        tabLayouts.setupWithViewPager(viewPager);
        setUpViewPager(viewPager);
        tabLayouts.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout)((ViewGroup) tabLayouts.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout)((ViewGroup) tabLayouts.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    //Este metodo ingresa el nombre de cada tab con su actividad
    public void setUpViewPager(ViewPager viewPager){

        TabViewPagerAdapter tabViewPagerAdapter= new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.addFragment(new Oppor_tab(),"Opportunities");
        tabViewPagerAdapter.addFragment(new Home_Tab(),"Home");
        tabViewPagerAdapter.addFragment(new Profile_tab(),"Profile");
        viewPager.setAdapter(tabViewPagerAdapter);
    }
}
