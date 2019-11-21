package com.development.SuiraApp.Fragments.ClientService;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.development.SuiraApp.R;

public class Suggestions_tab extends Fragment {


    Button btn_suggestion;
    EditText frm_suggestion;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_suggestions, container, false);

        btn_suggestion= view.findViewById(R.id.btn_sendSugges);
        frm_suggestion = view.findViewById(R.id.frm_suggestion);

        btn_suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Enviar sugerencia",Toast.LENGTH_SHORT).show();
                frm_suggestion.setText("");
            }
        });
        return view;
    }
}

