package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.Date;

public class Register extends AppCompatActivity {

    Button btn_nextR;
    EditText name;
    EditText lastName;
    EditText email;
    EditText password;
    CheckBox terms;
    CheckBox privacy;
    DatabaseReference dbUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inflation

        name= findViewById(R.id.etxt_name);
        lastName = findViewById(R.id.etxt_lastname);
        email = findViewById(R.id.etxt_mail);
        password = findViewById(R.id.etxt_password);
        terms = findViewById(R.id.checkBox_terms);
        privacy = findViewById(R.id.checkBox_policies);

        // Intitialize Reference
        FirebaseDatabase dbSuira = FirebaseDatabase.getInstance();
        dbUsers = dbSuira.getReference("userClient");


        btn_nextR=findViewById(R.id.nextR);
        btn_nextR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validate Data Fields
                if (validateForm())
                {
                    Intent intent= new Intent(getApplicationContext(), Register2.class);
                    intent.putExtra("userId", registerUser());
                    startActivity(intent);
                }

            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;
        String name_value = name.getText().toString() ;
        if (TextUtils.isEmpty(name_value)) {
            name.setError("Required.");
            valid = false;
        } else {
            name.setError(null);
        }
        String lastName_value = lastName.getText().toString() ;
        if (TextUtils.isEmpty(lastName_value)) {
            lastName.setError("Required.");
            valid = false;
        } else {
            lastName.setError(null);
        }
        String password_value = password.getText().toString();
        if (TextUtils.isEmpty(password_value)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }
        String email_value = email.getText().toString();
        if (TextUtils.isEmpty(email_value)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }
        if (!terms.isChecked()) {
            terms.setError("Required.");
            valid = false;
        } else {
            terms.setError(null);
        }
        if (!privacy.isChecked()) {
            privacy.setError("Required.");
            valid = false;
        } else {
            privacy.setError(null);
        }
        return valid;
    }

    private String registerUser(){
        String name_value = name.getText().toString() ;
        String lastname_value= lastName.getText().toString();
        String email_value=email.getText().toString();
        String password_value=password.getText().toString();

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        UserClientClass user= new UserClientClass(name_value,lastname_value,email_value,password_value,ts);
        String id = dbUsers.push().getKey();

        dbUsers.child(id).setValue(user);

        return(id);

    }
}
