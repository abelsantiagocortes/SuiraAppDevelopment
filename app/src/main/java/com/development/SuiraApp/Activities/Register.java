package com.development.SuiraApp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.development.SuiraApp.R;
import com.development.SuiraApp.Model.UserClientClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
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
    String emailV;
    String passwordV;
    DatabaseReference dbUsers;
    FirebaseAuth registerAuth;
    private static final String TAG = "Register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inflation

        name = findViewById(R.id.etxt_name);
        lastName = findViewById(R.id.etxt_lastname);
        email = findViewById(R.id.etxt_mail);
        password = findViewById(R.id.etxt_password);
        terms = findViewById(R.id.checkBox_terms);
        privacy = findViewById(R.id.checkBox_policies);

        emailV = email.getText().toString();
        passwordV = password.getText().toString();

        // Intitialize Reference
        FirebaseDatabase dbSuira = FirebaseDatabase.getInstance();
        dbUsers = dbSuira.getReference("userClient");
        // Intitialize Authentication
        registerAuth = FirebaseAuth.getInstance();


        btn_nextR = findViewById(R.id.nextR);
        btn_nextR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();
            }
        });
    }

    private void authenticate()
    {
        if(validateForm())
        {
            emailV = email.getText().toString();
            passwordV = password.getText().toString();
            registerAuth.createUserWithEmailAndPassword(emailV, passwordV)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = registerAuth.getCurrentUser();
                                //Registra en Base de Datos
                                registerUser(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                                registerUser(null);
                            }

                        }
                    });
        }

    }

    private void registerUser(FirebaseUser currentUser){
        if(currentUser!=null){
            Intent intent= new Intent(getApplicationContext(), Register2.class);
            intent.putExtra("userObject", (Serializable) registerUserDB());
            startActivity(intent);
        } else {
            email.setText("");
            password.setText("");
        }
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
        };
        if(!isEmailValid(email_value)){
            email.setText("");
            email.setError("Required.");
            valid = false;
        }
        else {
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
    private UserClientClass registerUserDB(){
        String name_value = name.getText().toString() ;
        String lastname_value= lastName.getText().toString();

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        UserClientClass user= new UserClientClass(name_value,lastname_value,ts);
        return user;

    }
    private boolean isEmailValid(String email) {
        if (!email.contains("@") ||
                !email.contains(".") ||
                email.length() < 5)
            return false;
        return true;
    }
}





