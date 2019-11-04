package com.development.SuiraApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.security.AccessController.getContext;

public class Notifications extends AppCompatActivity implements NotificationsAdapter.OnSeeListener {

    private RecyclerView recyclerView;
    public List<NotificationClass> notifications;
    public ArrayList<WrapperNotification> wrapperList;
    private FirebaseAuth signOutAuth;
    private NotificationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_notifications);

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        signOutAuth = FirebaseAuth.getInstance();



        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);


        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);


        wrapperList = new ArrayList<>();
        FirebaseUser currentUser = signOutAuth.getCurrentUser();
        String userId = currentUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("notification").orderByChild("userId").equalTo(userId);

        query.addListenerForSingleValueEvent(valueEventListener);

        ;
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists())
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NotificationClass noti = snapshot.getValue(NotificationClass.class);
                    String k = snapshot.getKey();
                    WrapperNotification wn = new WrapperNotification(noti , k);

                    System.out.println(snapshot.getKey() + " tiene " + noti.getOpportunityId());
                    wrapperList.add(wn);
                }
            }

            Collections.sort(wrapperList);

            notifications = generateNotificationsList(wrapperList);
            initializeAdapter(notifications);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void initializeAdapter(List<NotificationClass> listi){
        NotificationsAdapter adapter = new NotificationsAdapter(listi ,  this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.hamburger_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
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
                signOutAuth.signOut();
                Intent intent2 = new Intent( getApplicationContext(), LogIn.class );
                startActivity(intent2);
                return true;
            case R.id.fourth_item:
                Intent intent3 = new Intent(getApplicationContext(),ComponentNetworks.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void OnSeeClick(int position) {
        /*
        aqui va el codigo
         */
        System.out.println("position: " + Integer.toString(position));
        updateSeen(wrapperList.get(position).getKey());

        Toast toast=Toast.makeText(getApplicationContext(), wrapperList.get(position).getKey() + "updated",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

    private void updateSeen( String notifID ){

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("notification").child(notifID).child("seen").setValue(true);


    }

    //genera un arraylist de notificationClass basado en el arreglo del wrapper
    private ArrayList<NotificationClass> generateNotificationsList(ArrayList<WrapperNotification> wnl){
        ArrayList<NotificationClass> notifs = new ArrayList<NotificationClass>();
        for(int i = 0 ; i < wnl.size() ; ++i){
            notifs.add(wnl.get(i).getNot());
        }
        return notifs;
    }
}


