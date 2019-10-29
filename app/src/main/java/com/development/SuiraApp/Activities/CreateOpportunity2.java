package com.development.SuiraApp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.development.SuiraApp.R;

public class CreateOpportunity2 extends AppCompatActivity {

    Button botonsito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_opportunity_2);

        botonsito=(Button)findViewById(R.id.button8);
        botonsito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }


}
