package com.development.SuiraApp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.development.SuiraApp.Adapters.AplicantsAdapter;
import com.development.SuiraApp.Model.Aplication;
import com.development.SuiraApp.Model.NotificationClass;
import com.development.SuiraApp.R;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AplicantesOportunidad extends AppCompatActivity implements AplicantsAdapter.OnAcceptListener , AplicantsAdapter.OnGoListener {

    private RecyclerView recyclerView;
    public List<Aplication> apps;
    private FirebaseAuth signOutAuth = FirebaseAuth.getInstance();
    private AplicantsAdapter adapter;
    private Map<String , byte[]> fotos = new HashMap<>();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    /******valor de la oportunidad quemado*****/
    private String oppID = "-Ltx8BVUpBVZjqYKO8jU";


    @Override
    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aplicantes_oportunidad);
        recyclerView = findViewById(R.id.recyclerAplicant);
        apps = new ArrayList<>();
        FirebaseUser currentUser = signOutAuth.getCurrentUser();
        String userId = currentUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("applications").orderByChild("opportunityId").equalTo(oppID);
        query.addListenerForSingleValueEvent(listener2);

        //apps.add(new Aplication("lCf0BCck04Mj1BGw3gDQ2P5dXKR2" , "-LtuuF3tD0fv0D8pLbIi"));
        initializeAdapter();

    }


    /**
     * Listener to retrieve information from the database only once
     */
    ValueEventListener listener2 = new ValueEventListener() {
        /**
         * gets the notifications from the database the first time
         * @param dataSnapshot wich the DB information
         */
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println("usuario "+ snapshot.getValue(Aplication.class).getApplicantId());
                    apps.add(snapshot.getValue(Aplication.class));
                }
            }

            initializeAdapter();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    /**
     * initializes the  cards
     */
    private void initializeAdapter(){
        AplicantsAdapter adapter = new AplicantsAdapter(apps ,this , this , fotos);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Creates and pushes a notification
     * @param position the position of the application in the arraylist
     */
    @Override
    public void OnAccept(int position) {
        FirebaseUser currentUser = signOutAuth.getCurrentUser();
        String userId = currentUser.getUid();

        NotificationClass noti = new NotificationClass();
        noti.setType("Accepted");
        noti.setOpportunityId(apps.get(position).getOpportunityId());
        noti.setDescription("");
        noti.setName("Hoy hace been accepted for an opportunity");
        noti.setPublisherId(userId);
        noti.setSeen(false);
        noti.setUserId(apps.get(position).getApplicantId());

        FirebaseDatabase dbSuira = FirebaseDatabase.getInstance();
        DatabaseReference dbNot = dbSuira.getReference("notification");
        String key = dbNot.push().getKey();
        System.out.println("Voy a meter la notificacion");
        Toast toast = Toast.makeText(getApplicationContext(), "You have accepted " + apps.get(position).getNombre() + "!", Toast.LENGTH_SHORT);
        toast.setMargin(50, 50);
        toast.show();
        dbNot.child(key).setValue(noti);
    }

    @Override
    public void OnGo(int position) {
        //TODO mandar al perfil
    }
}


