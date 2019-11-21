package com.development.SuiraApp.Fragments.Opportunities;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Adapters.MyViewPagerAdapter;
import com.development.SuiraApp.Adapters.Opportunities.ApplicationAdapter;
import com.development.SuiraApp.Adapters.Opportunities.OpportunitiesAdapter;
import com.development.SuiraApp.Model.ApplicationClass;
import com.development.SuiraApp.Model.OpportunityClass;
import com.development.SuiraApp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OpporAppl_tab extends Fragment {

    private MyViewPagerAdapter adapter;
    RecyclerView recyclerView;
    public List<ApplicationClass> apis;
    FirebaseAuth signOutAuth;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_opporappl, container, false);

        recyclerView = view.findViewById(R.id.recyclerAppis);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);
        apis = new ArrayList<>();

        signOutAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = signOutAuth.getCurrentUser();
        String userId = currentUser.getUid();

        Query queryApps = FirebaseDatabase.getInstance().getReference("applications").orderByChild("applicantId").equalTo(userId);
        queryApps.addValueEventListener(valueEventListener);

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
                    ApplicationClass app = snapshot.getValue(ApplicationClass.class);
                    apis.add(app);
                }
            }
            initializeAdapter(apis);

        }


        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void initializeAdapter(List<ApplicationClass> listi){
        ApplicationAdapter adapter = new ApplicationAdapter(listi);
        recyclerView.setAdapter(adapter);
    }
}
