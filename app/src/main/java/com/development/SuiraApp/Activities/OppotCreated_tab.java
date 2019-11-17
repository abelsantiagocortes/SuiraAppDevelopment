package com.development.SuiraApp.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Adapters.NotificationsAdapter;
import com.development.SuiraApp.Adapters.OpportunitiesAdapter;
import com.development.SuiraApp.Model.NotificationClass;
import com.development.SuiraApp.Model.OpportunityClass;
import com.development.SuiraApp.Model.WrapperNotification;
import com.development.SuiraApp.MyViewPagerAdapter;
import com.development.SuiraApp.R;
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

public class OppotCreated_tab extends Fragment  {

    private MyViewPagerAdapter adapter;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase dbSuira;
    DatabaseReference dbOppo;
    RecyclerView recyclerView;
    public List<OpportunityClass> opos;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseAuth signOutAuth;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_opporcreated, container, false);

        recyclerView =view.findViewById(R.id.recyclerOpps);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);
        opos= new ArrayList<>();

        signOutAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = signOutAuth.getCurrentUser();
        String userId = currentUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("opportunities").orderByChild("publisherId").equalTo(userId);
        query.addValueEventListener(valueEventListener);

        return  view;
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        /**
         * gets the notifications from the database
         * @param dataSnapshot with the DB information
         */
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists())
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OpportunityClass opp = snapshot.getValue(OpportunityClass.class);
                    opos.add(opp);
                }
            }

            initializeAdapter(opos);


        }


        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void initializeAdapter(List<OpportunityClass> listi){
        OpportunitiesAdapter adapter = new OpportunitiesAdapter(listi);
        recyclerView.setAdapter(adapter);
    }


}

