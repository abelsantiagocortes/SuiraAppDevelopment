package com.development.SuiraApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.development.SuiraApp.Activities.AplicantesOportunidad;
import com.development.SuiraApp.Activities.CreateOpportunity;
import com.development.SuiraApp.Activities.Home_Tab;
import com.development.SuiraApp.Activities.LandingPage;
import com.development.SuiraApp.Activities.LogIn;
import com.development.SuiraApp.Activities.Notifications;
import com.development.SuiraApp.Activities.Oppor_tab;
import com.development.SuiraApp.Activities.PopUpContactInfo;
import com.development.SuiraApp.Activities.Profile_tab;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomeUserClient extends AppCompatActivity {


    //Se definen el tab(donde estarán todas las posibilidades de tab) Inferior
    //Se define el viewPager que es el que muestra lo que tiene cada actividad por aparte
    TabLayout tabLayouts;
    ViewPager viewPager;
    TextView txt;
    ImageView lg;

    private FirebaseAuth signOutAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user_client);
        //Se infla tool bar para el hamburguesa
        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);

        //Se inflan tab y view a modificar
        tabLayouts=(TabLayout) findViewById(R.id.tabLayoutHome);
        viewPager=(ViewPager) findViewById(R.id.viewPagerHome);

        signOutAuth = FirebaseAuth.getInstance();
        txt = findViewById(R.id.textView17);
        lg = findViewById(R.id.logoOut);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                startActivity(intent);
            }
        });



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
        tabViewPagerAdapter.addFragment(new Notifications(),"Home");
        tabViewPagerAdapter.addFragment(new Profile_tab(),"Profile");
        viewPager.setAdapter(tabViewPagerAdapter);
    }
    /**
     * inflates the notification cards
     * @param menu hamburger menu
     * @return true if it can be created
     */

    public boolean onCreateOptionsMenu( Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.hamburger_menu, menu);
        return true;
    }

    public void onBackPressed() {
    }

    /**
     * options for the hamburger menu
     * @param item hamburger menu
     * @return true if it can be creted
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId())
        {
            case R.id.first_item:
                Intent intent = new Intent( getApplicationContext(), CreateOpportunity.class );
                startActivity(intent);
                return true;
            case R.id.second_item:
                Intent intent1 = new Intent( getApplicationContext(), Notifications.class );
                startActivity(intent1);
                return true;
            case R.id.third_item:
                signOutAuth.signOut();
                Intent intent2 = new Intent( getApplicationContext(), LogIn.class );
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
