package com.development.SuiraApp.Activities.Opportunities;

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
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.development.SuiraApp.Activities.Authentication.LogIn;
import com.development.SuiraApp.Activities.Opportunities.CreateOpportunity;
import com.development.SuiraApp.Activities.Opportunities.HomeUserClient;
import com.development.SuiraApp.Activities.Opportunities.Notifications;
import com.development.SuiraApp.Model.ApplicationClass;
import com.development.SuiraApp.Model.OpportunityClass;
import com.development.SuiraApp.Model.UserClientClass;
import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.StringTokenizer;

public class OpportunityInfo extends AppCompatActivity {

    FirebaseAuth userClient;
    FirebaseUser user;
    FirebaseAuth currentUser;
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
    DatabaseReference databaseApplications;
    DatabaseReference creatApp;
    ApplicationClass api;

    /**
     * Initializes the GUI-Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity_info);

        Toolbar toolbar = findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        Bundle contenidos;
        contenidos = getIntent().getExtras();
        final String oppId = contenidos.getString("OppId");

        creatApp = FirebaseDatabase.getInstance().getReference("applications");
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

        api = new ApplicationClass();

        Query query = FirebaseDatabase.getInstance().getReference("opportunities").child(oppId);
        //Query query = FirebaseDatabase.getInstance().getReference("opportunities").child("-LtwdhFfxJZeKk82PaP3");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OpportunityClass oppor = dataSnapshot.getValue(OpportunityClass.class);
                api.setOpp(oppor);
                oppNameT.setText(oppor.getName());
                description.setText(oppor.getDescription());
                if( oppor.getTags().size() > 0 )
                {
                    String auxTag;
                    StringTokenizer st = new StringTokenizer(oppor.getTags().get(0));
                    for(int i = 1 ; st.hasMoreTokens(); i++)
                    {
                        auxTag = st.nextToken();
                        if( i == 1 )
                        {
                            tag1.setText(auxTag);
                            tag1.setVisibility(View.VISIBLE);
                        }
                        if( i == 2 )
                        {
                            tag2.setText(auxTag);
                            tag2.setVisibility(View.VISIBLE);
                        }
                        if( i == 3)
                        {
                            tag3.setText(auxTag);
                            tag3.setVisibility(View.VISIBLE);
                        }
                        if( i == 4 )
                        {
                            tag4.setText(auxTag);
                            tag4.setVisibility(View.VISIBLE);
                        }
                        if( i == 5 )
                        {
                            tag5.setText(auxTag);
                            tag5.setVisibility(View.VISIBLE);
                        }
                    }
                }
                date.setText(oppor.getEndDate());
                localInfo.setText(oppor.getLocation());
                String priceO = String.valueOf(oppor.getBudget());
                price.setText(priceO);

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
                        String name = user.getName() + " " + user.getLastName();
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

        currentUser = FirebaseAuth.getInstance();
        user = currentUser.getCurrentUser();
        final String userId = user.getUid();
        apl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query nombreApl = FirebaseDatabase.getInstance().getReference("userClient").child(userId);
                nombreApl.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserClientClass userAux = dataSnapshot.getValue(UserClientClass.class);
                        String myName = userAux.getName();

                        String appId = creatApp.push().getKey();
                        api.setOpportunityId(oppId);
                        api.setApplicantId(userId);
                        api.setNombre(myName);
                        //ApplicationClass application = new ApplicationClass( "-LtwdhFfxJZeKk82PaP3", userId, myName);
                        creatApp.child(appId).setValue(api);
                        Toast.makeText(getApplicationContext(), "¡Gracias por Aplicar!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent( getApplicationContext(), HomeUserClient.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    /**
     * Initializes the menu of the toolbar
     * @param menu menu pre-made
     * @return true if the initialization was successful
     */
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.hamburger_menu, menu);
        return true;
    }

    /**
     * Reads which option of the menu was seleceted
     * @param item the item selected
     * @return true if it was able to return the item
     */
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
