package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

                Bundle bund = new Bundle();

                String msn = new String("Thanks for Creating Your Opportunity \n \n \n Suira is Working To Find What You Need");
                String btnMsn = new String("Create");
                String activityName = new String("Notifications");

                Intent intent= new Intent(getApplicationContext(), PopUp.class);

                bund.putString("mensaje", msn);
                bund.putString("contenidoBoton", btnMsn);
                bund.putString("sender", activityName );
                intent.putExtras(bund);

                startActivity(intent);
            }
        });
    }


}
