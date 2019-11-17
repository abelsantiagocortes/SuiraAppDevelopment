package com.development.SuiraApp.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.development.SuiraApp.MyViewPagerAdapter;
import com.development.SuiraApp.R;

public class Home_Tab extends Fragment {

    private MyViewPagerAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home, container, false);
        return  view;
    }
}