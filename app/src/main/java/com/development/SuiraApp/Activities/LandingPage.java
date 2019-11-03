package com.development.SuiraApp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.development.SuiraApp.R;
import com.development.SuiraApp.SCHome;

public class LandingPage extends AppCompatActivity {

    Button btn_logIn;
    Button btn_register;
    Button btn_register2;
    Button btn_buscar;
    TextView faq;
    TextView sugg;
    TextView terms;
    TextView email;
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
                Intent intent= new Intent(getApplicationContext(), LogIn.class);
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
                Toast.makeText(getApplicationContext(), "Upcoming Feature",
                        Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(), ProfileTest.class);
                startActivity(intent);
            }
        });

        faq= findViewById(R.id.textView9);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SCHome.class);
                startActivity(intent);

            }
        });

        sugg= findViewById(R.id.textView10);
        sugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SCHome.class);
                startActivity(intent);
            }
        });
        terms = findViewById(R.id.etxt_description);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SCHome.class);
                startActivity(intent);
            }
        });
        email = findViewById(R.id.textView11);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "suira.org@gmail.com"));
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
    }

}
