package com.development.SuiraApp.Activities.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.development.SuiraApp.Activities.Authentication.LogIn;
import com.development.SuiraApp.Model.UserClientClass;
import com.development.SuiraApp.PopUps.PopUp;
import com.development.SuiraApp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AditionalInfo extends AppCompatActivity {

    private FirebaseAuth registerAuth;
    private DatabaseReference dbUsers;
    private EditText location;
    private EditText phone;
    private EditText linkFacebook;
    private EditText linkInstagram;
    private EditText linkSpotify;
    private EditText linkYoutube;
    private EditText description;
    private Button btnEnviar;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_aditional);

        registerAuth = FirebaseAuth.getInstance();
        dbUsers = FirebaseDatabase.getInstance().getReference("userClient");
        FirebaseUser currentUser = registerAuth.getCurrentUser();
        final String userId = currentUser.getUid();
        location = findViewById(R.id.elocation);
        phone = findViewById(R.id.ephone);
        linkFacebook = findViewById(R.id.eFacebook);
        linkInstagram = findViewById(R.id.eInstagram);
        linkSpotify = findViewById(R.id.eSpotify);
        linkYoutube = findViewById(R.id.eYoutube);
        description = findViewById(R.id.edescription);
        btnEnviar = findViewById(R.id.registerA);
        skip = findViewById(R.id.skip);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            /**
             * inserta la información en la base de datos que el usuario ha ingresado
             * @param view
             */
            @Override
            public void onClick(View view) {
                UserClientClass user;
                boolean forma = validateForm();
                    if(forma==true){
                         user = (UserClientClass) getIntent().getSerializableExtra("userObject");
                         user.setDescription(description.getText().toString());
                         user.setLinkFacebook(linkFacebook.getText().toString());
                         user.setLinkInstagram(linkInstagram.getText().toString());
                         user.setLinkSpotify(linkSpotify.getText().toString());
                         user.setLinkYoutube(linkYoutube.getText().toString());
                         user.setLocation(location.getText().toString());
                         user.setPhone(phone.getText().toString());
                        dbUsers.child(userId).setValue(user);
                    }else{
                        Bundle bund = new Bundle();

                        String msn = "No information was written!";
                        String btnMsn = "OK";

                        Intent intentP= new Intent(getApplicationContext(), PopUp.class);

                        bund.putString("mensaje",msn);
                        bund.putString("contenidoBoton", btnMsn);
                        intentP.putExtras(bund);

                        startActivity(intentP);

                    }

                Intent intentLogin= new Intent(getApplicationContext(), LogIn.class);
                startActivity(intentLogin);
                finish();
            }


        });

        skip.setOnClickListener(new View.OnClickListener() {
            /**
             * inserta lleva al usuario a la actividad de LogIn
             * @param view
             */
            @Override
            public void onClick(View view) {

                Intent intentLogin= new Intent(getApplicationContext(), LogIn.class);
                startActivity(intentLogin);
                finish();

            }


        });



    }

    /**
     * Valida que todos los campos adicionales no esten vacios
     * @return boolean
     */
    public boolean validateForm(){
        String valueLocation = location.getText().toString();
        String valuePhone = phone.getText().toString();
        String valueFacebook = linkFacebook.getText().toString();
        String valueInstagram = linkInstagram.getText().toString();
        String valueSpotify = linkSpotify.getText().toString();
        String valueYoutube = linkYoutube.getText().toString();
        String valueDescription = description.getText().toString();
        if(TextUtils.isEmpty(valueDescription)&&TextUtils.isEmpty(valueFacebook)&&TextUtils.isEmpty(valueInstagram)&&TextUtils.isEmpty(valueLocation)&&
                TextUtils.isEmpty(valuePhone)&&TextUtils.isEmpty(valueSpotify)&&TextUtils.isEmpty(valueYoutube)){
            return false;

        }else{
            return true;
        }


    }
}
