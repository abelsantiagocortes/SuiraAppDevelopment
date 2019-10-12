package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<NotificationClass> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notifications);

        recyclerView = (RecyclerView) findViewById(R.id.rv);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
;
    }

    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Lavery Maiss", "25 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));
        persons.add(new NotificationClass("Emma Wilson", "23 years old", R.drawable.enistein));


    }


    private void initializeAdapter(){
        NotificationsAdapter adapter = new NotificationsAdapter(persons);
        recyclerView.setAdapter(adapter);
    }




}
