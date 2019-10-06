package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Log_in_Layout extends AppCompatActivity {
    /* INFLATE OBJECTS */

    Button btn_log_in;
    TextView txt_sign_up;
    TextView txt_forgot_password;
    EditText frm_email;
    EditText frm_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in__layout);

        /* INFLATE OBJECTS */
        btn_log_in=(Button) findViewById(R.id.btn_log_in);
        txt_sign_up=(TextView) findViewById(R.id.txt_sign_up);
        txt_forgot_password=(TextView) findViewById(R.id.txt_forgot_password);
        frm_email=(EditText) findViewById(R.id.frm_email);
        frm_password=(EditText) findViewById(R.id.frm_password);

        /* CLICK LISTENERS */
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext(),Forgot_Password_Layout.class);
                startActivity(inte);
            }
        });
    }
}
