package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ComponentNetworks extends AppCompatActivity {

    private FirebaseAuth signOutAuth;
    private FirebaseUser currentUser;
    private String userId;
    private UserClientClass user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.component_network);
        signOutAuth = FirebaseAuth.getInstance();
        currentUser = signOutAuth.getCurrentUser();
        userId = currentUser.getUid();
        Query consulta = FirebaseDatabase.getInstance().getReference("userClient").orderByKey().equalTo(userId);
        consulta.addListenerForSingleValueEvent(valueEventListener);
        final Button facebookButton = findViewById(R.id.buttonFacebook);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(user.getLinkFacebook());
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW,uri);
                if (facebookIntent.resolveActivity(getPackageManager()) != null) {

                    startActivity(facebookIntent);
                }

            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    user = snapshot.getValue(UserClientClass.class);

                }


            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
