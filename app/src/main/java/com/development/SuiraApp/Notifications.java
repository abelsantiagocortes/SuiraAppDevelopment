package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

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

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);


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
