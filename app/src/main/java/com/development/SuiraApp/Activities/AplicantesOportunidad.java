package com.development.SuiraApp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.development.SuiraApp.Adapters.AplicantsAdapter;
import com.development.SuiraApp.Model.ApplicationClass;
import com.development.SuiraApp.Model.NotificationClass;
import com.development.SuiraApp.Model.OpportunityClass;
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
    public List<ApplicationClass> apps;
    public String ID;
    private FirebaseAuth signOutAuth = FirebaseAuth.getInstance();
    private AplicantsAdapter adapter;
    private Map<String , byte[]> fotos = new HashMap<>();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();


    @Override
    public void onCreate(Bundle savedInstanceState) {


        /**/
        super.onCreate(savedInstanceState);

        String userId = getIntent().getStringExtra("userId");


        setContentView(R.layout.activity_aplicantes_oportunidad);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerAplicant);
        signOutAuth = FirebaseAuth.getInstance();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);



        apps = new ArrayList<>();
        Query query = FirebaseDatabase.getInstance().getReference("applications").orderByChild("opportunityId").equalTo(userId);
        query.addValueEventListener(listener2);

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
                System.out.println("si exiiiiiste");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    apps.add(snapshot.getValue(ApplicationClass.class));
                }
                System.out.println("tam de la lista: "+ Integer.toString(apps.size()));
                initializeAdapter();
            }
            else{
                System.out.println("No exiiiiiiste");
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
        for(int i = 0 ; i < apps.size() ; ++i) {
            System.out.println(apps.get(i).getOpportunityId());
        }
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


