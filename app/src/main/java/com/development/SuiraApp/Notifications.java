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

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class Notifications extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<NotificationClass> persons;
    private FirebaseAuth signOutAuth;

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


        initializeData();
        initializeAdapter();
;
    }

    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new NotificationClass("Abelardo C. ", "Diseñador de Horarios", R.drawable.designer));
        persons.add(new NotificationClass("Seposorio G.", "Toque en Santo de Angel", R.drawable.camera));
        persons.add(new NotificationClass("Kamilin S.", "Organizador de Escritorio", R.drawable.fashion));
        persons.add(new NotificationClass("Dani M.", "Director de Video", R.drawable.paint));
        persons.add(new NotificationClass("Deivid S", "Profesor de Vocabulario", R.drawable.wtri));
        persons.add(new NotificationClass("Julieto M.", "Doctor 24/7", R.drawable.record));
        persons.add(new NotificationClass("Abelardo C. ", "Diseñador de Horarios", R.drawable.designer));
        persons.add(new NotificationClass("Seposorio G.", "Toque en Santo de Angel", R.drawable.camera));
        persons.add(new NotificationClass("Kamilin S.", "Organizador de Escritorio", R.drawable.fashion));
        persons.add(new NotificationClass("Dani M.", "Director de Video", R.drawable.paint));
        persons.add(new NotificationClass("Deivid S", "Profesor de Vocabulario", R.drawable.wtri));
        persons.add(new NotificationClass("Julieto M.", "Doctor 24/7", R.drawable.record));
        persons.add(new NotificationClass("Abelardo C. ", "Diseñador de Horarios", R.drawable.designer));
        persons.add(new NotificationClass("Seposorio G.", "Toque en Santo de Angel", R.drawable.camera));
        persons.add(new NotificationClass("Kamilin S.", "Organizador de Escritorio", R.drawable.fashion));
        persons.add(new NotificationClass("Dani M.", "Director de Video", R.drawable.paint));
        persons.add(new NotificationClass("Deivid S", "Profesor de Vocabulario", R.drawable.wtri));
        persons.add(new NotificationClass("Julieto M.", "Doctor 24/7", R.drawable.record));



    }

    private void initializeAdapter(){
        NotificationsAdapter adapter = new NotificationsAdapter(persons);
        recyclerView.setAdapter(adapter);
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
                signOutAuth.signOut();
                Intent intent2 = new Intent( getApplicationContext(), LogIn.class );
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
