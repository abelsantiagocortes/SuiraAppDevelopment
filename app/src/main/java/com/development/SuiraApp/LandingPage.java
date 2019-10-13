package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LandingPage extends AppCompatActivity {

    Button btn_logIn;
    Button btn_register;
    Button btn_register2;
    Button btn_buscar;
    LinearLayout ln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);



        ConstraintLayout c1 = (ConstraintLayout)findViewById(R.id.con1);
        ConstraintLayout c2 = (ConstraintLayout)findViewById(R.id.con2);

        LayoutInflater inflater = LayoutInflater.from(this);
        ConstraintLayout  cl1= (ConstraintLayout) inflater.inflate(R.layout.landing_page1, null);
        ConstraintLayout  cl2= (ConstraintLayout) inflater.inflate(R.layout.landing_page2, null);

        c1.addView(cl1);
        c2.addView(cl2);

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        btn_logIn=(Button)findViewById(R.id.btn_1_3);
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
            }
        });

        btn_register=(Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), CreateOpportunity.class);
                startActivity(intent);
            }
        });

        btn_register2=(Button)findViewById(R.id.btn_register2);
        btn_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        btn_buscar=(Button)findViewById(R.id.bnt_buscar);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Notifications.class);
                startActivity(intent);
            }
        });

    }
}
