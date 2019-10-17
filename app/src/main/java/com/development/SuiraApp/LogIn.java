package com.development.SuiraApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private FirebaseAuth loginAuth;
    private static final String TAG = "LogIn";


    /* INFLATE OBJECTS */

    Button btn_log_in;
    TextView txt_sign_up;
    TextView txt_forgot_password;
    EditText frm_email;
    EditText frm_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /* INFLATE OBJECTS */
        btn_log_in=(Button) findViewById(R.id.btn_log_in);
        txt_sign_up=(TextView) findViewById(R.id.txt_sign_up);
        txt_forgot_password=(TextView) findViewById(R.id.txt_forgot_password);
        frm_email=(EditText) findViewById(R.id.frm_email);
        frm_password=(EditText) findViewById(R.id.frm_password);

        loginAuth = FirebaseAuth.getInstance();

        /* CLICK LISTENERS */
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(frm_email.getText().toString(), frm_password.getText().toString());
            }
        });
        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext(), Register.class);
                startActivity(inte);

            }
        });
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(inte);
            }
        });

    }
    /* Authentication */
    @Override
    public void onStart() {
        super.onStart();
        // Revisa si el usuario existe
        FirebaseUser currentUser = loginAuth.getCurrentUser();
        logInUser(currentUser);
    }

    public void onBackPressed() {
    }


    private void logInUser(FirebaseUser currentUser){
        Intent intent;
        if(currentUser!=null){
            String x= getIntent().getStringExtra("landing");

                intent = new Intent(getBaseContext(), Notifications.class);

            startActivity(intent);
        } else {
            frm_email.setText("");
            frm_password.setText("");
        }
    }
     //Field Validation

    private boolean validateForm() {
        boolean valid = true;
        String email = frm_email.getText().toString();
        if(!isEmailValid(email)){
            frm_email.setText("");
            frm_email.setError("Required.");
            valid = false;
        }
        else {
            frm_email.setError(null);
        }

        if (TextUtils.isEmpty(email)) {
            frm_email.setError("Required.");
            valid = false;
        } else {
            frm_email.setError(null);
        }
        String password = frm_password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            frm_password.setError("Required.");
            valid = false;
        } else {
            frm_password.setError(null);
        }
        return valid;
    }

    private void signInUser(String email, String password) {
        if (validateForm()) {
            loginAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = loginAuth.getCurrentUser();
                                logInUser(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                logInUser(null);
                            }
                        }
                    });
        }
    }

    private boolean isEmailValid(String email) {
        if (!email.contains("@") ||
                !email.contains(".") ||
                email.length() < 5)
            return false;
        return true;
    }
}
