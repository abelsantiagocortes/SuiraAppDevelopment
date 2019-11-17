package com.development.SuiraApp.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.development.SuiraApp.Adapters.MyViewPagerAdapter;
import com.development.SuiraApp.R;
import com.development.SuiraApp.Adapters.TabViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Oppor_tab extends Fragment {

    private MyViewPagerAdapter adapter;

    //Se definen el tab(donde estarán todas las posibilidades de tab) Inferior
    //Se define el viewPager que es el que muestra lo que tiene cada actividad por aparte
    TabLayout tabLayouts;
    ViewPager viewPager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_opportunities, container, false);
        //Se inflan tab y view a modificar
        tabLayouts=(TabLayout) view.findViewById(R.id.tabLayoutOppor);
        viewPager=(ViewPager) view.findViewById(R.id.viewPagerOppor);

        //Le mete el viewpager al tablayout
        tabLayouts.setupWithViewPager(viewPager);
        setUpViewPager(viewPager);
        return  view;
    }

    //Este metodo ingresa el nombre de cada tab con su actividad
    public void setUpViewPager(ViewPager viewPager){

        TabViewPagerAdapter tabViewPagerAdapter= new TabViewPagerAdapter(getChildFragmentManager());
        tabViewPagerAdapter.addFragment(new OpporAppl_tab(),"Aplicaciones");
        tabViewPagerAdapter.addFragment(new OppotCreated_tab(),"Oportunidades");
        viewPager.setAdapter(tabViewPagerAdapter);
    }
}