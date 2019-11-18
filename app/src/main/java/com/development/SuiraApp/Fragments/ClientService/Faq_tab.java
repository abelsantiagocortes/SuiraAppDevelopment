package com.development.SuiraApp.Fragments.ClientService;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.development.SuiraApp.Adapters.MyViewPagerAdapter;
import com.development.SuiraApp.R;

public class Faq_tab extends Fragment {

    private MyViewPagerAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_faq, container, false);
        return  view;
    }
}