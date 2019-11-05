package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_profile extends AppCompatActivity {
    private Button btn_solicitation;
    private Button btn_recomend;
    private FirebaseAuth userAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btn_recomend = (Button)findViewById(R.id.btn_recomend);
        btn_solicitation = (Button)findViewById(R.id.btn_infSolicitation);
        userAuth = FirebaseAuth.getInstance();
        final String myId;
        final String recomendedUsrId;
        FirebaseUser usr = userAuth.getCurrentUser();
        myId = usr.getUid();

        btn_recomend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_solicitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationClass infoNotification = new NotificationClass("Information request", myId,recomendedUsrId,"InfReq");

            }
        });
    }
}
