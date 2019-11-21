package com.development.SuiraApp.Activities.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.development.SuiraApp.R;

public class ForgotPassword extends AppCompatActivity {
    /* INFLATE OBJECTS */

    Button btn_send;
    EditText frm_email_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        /* INFLATE OBJECTS */

        btn_send=(Button) findViewById(R.id.btn_send);
        frm_email_forgot=(EditText) findViewById(R.id.frm_email_forgot);

        /* CLICK LISTENERS */
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
