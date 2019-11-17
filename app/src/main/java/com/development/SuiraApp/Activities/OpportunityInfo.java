package com.development.SuiraApp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.development.SuiraApp.Model.OpportunityClass;
import com.development.SuiraApp.Model.UserClientClass;
import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class OpportunityInfo extends AppCompatActivity {

    FirebaseAuth userClient;
    CircularImageView imgProfile;
    TextView oppNameT;
    TextView nameProfile;
    TextView description;
    Button tag1;
    Button tag2;
    Button tag3;
    Button tag4;
    Button tag5;
    TextView localInfo;
    TextView date;
    TextView price;
    Button apl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity_info);

        Toolbar toolbar = findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        oppNameT = findViewById(R.id.oppName);
        imgProfile = findViewById(R.id.profileOppA);
        nameProfile = findViewById(R.id.nameOppCreator);
        description = findViewById(R.id.description);

        tag1 = findViewById(R.id.tagSubOpp1);
        tag2 = findViewById(R.id.tagSubOpp2);
        tag3 = findViewById(R.id.tagSubOpp3);
        tag4 = findViewById(R.id.tagSubOpp4);
        tag5 = findViewById(R.id.tagSubOpp5);

        localInfo = findViewById(R.id.locationInfo);
        date = findViewById(R.id.calendarInfo);
        price = findViewById(R.id.priceInfo);

        apl = findViewById(R.id.aplicarStatic);

        tag1.setVisibility(View.INVISIBLE);
        tag2.setVisibility(View.INVISIBLE);
        tag3.setVisibility(View.INVISIBLE);
        tag4.setVisibility(View.INVISIBLE);
        tag5.setVisibility(View.INVISIBLE);

        //String id = userClient.getCurrentUser().getUid();

        Query query = FirebaseDatabase.getInstance().getReference("opportunities").child("-LtueJMWohDaWX_fMls2");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OpportunityClass oppor = dataSnapshot.getValue(OpportunityClass.class);
                oppNameT.setText(oppor.getName());
                description.setText(oppor.getDescription());
                if( oppor.getTags().size() > 0 )
                {
                    int aux = oppor.getTags().size();
                    tag1.setText(oppor.getTags().get(0));
                    tag1.setVisibility(View.VISIBLE);
                    if( aux > 1)
                    {
                        tag2.setText(oppor.getTags().get(1));
                        tag2.setVisibility(View.VISIBLE);
                    }
                    if( aux > 2 )
                    {
                        tag3.setText(oppor.getTags().get(2));
                        tag3.setVisibility(View.VISIBLE);
                    }
                    if( aux > 3 )
                    {
                        tag4.setText(oppor.getTags().get(3));
                        tag4.setVisibility(View.VISIBLE);
                    }
                    if( aux > 4 )
                    {
                        tag5.setText(oppor.getTags().get(4));
                        tag5.setVisibility(View.VISIBLE);
                    }
                }

                String id = oppor.getPublisherId();

                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                storageRef.child("images/userClient/" + id).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bMap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imgProfile.setImageBitmap(bMap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        System.out.println("No se pudo sacar la foto");
                    }
                });

                Query queryIntra = FirebaseDatabase.getInstance().getReference("userClient").child(id);
                queryIntra.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserClientClass user = dataSnapshot.getValue(UserClientClass.class);
                        String name = user.getName() + "\n" + user.getLastName();
                        nameProfile.setText(name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.hamburger_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId())
        {
            case R.id.first_item:
                Intent intent = new Intent( getApplicationContext(), CreateOpportunity.class );
                startActivity(intent);
                return true;
            case R.id.second_item:
                Intent intent1 = new Intent( getApplicationContext(), Notifications.class );
                startActivity(intent1);
                return true;
            case R.id.third_item:
                userClient.signOut();
                Intent intent2 = new Intent( getApplicationContext(), LogIn.class );
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
