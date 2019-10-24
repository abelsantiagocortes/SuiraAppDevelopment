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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.security.AccessController.getContext;

public class Notifications extends AppCompatActivity {

    private RecyclerView recyclerView;
    public List<NotificationClass> notifications;
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


        notifications = new ArrayList<>();
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
                    notifications.add(noti);
                }
            }

            Collections.sort(notifications);

            initializeAdapter(notifications);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void initializeAdapter(List<NotificationClass> listi){
        NotificationsAdapter adapter = new NotificationsAdapter(listi);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
