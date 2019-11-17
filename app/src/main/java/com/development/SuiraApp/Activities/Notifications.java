package com.development.SuiraApp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.development.SuiraApp.Model.NotificationClass;
import com.development.SuiraApp.Adapters.NotificationsAdapter;
import com.development.SuiraApp.R;
import com.development.SuiraApp.Model.WrapperNotification;
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

public class Notifications extends Fragment implements NotificationsAdapter.OnSeeListener , NotificationsAdapter.OnDismissListener {

    private RecyclerView recyclerView;
    public List<NotificationClass> notifications;
    public ArrayList<WrapperNotification> wrapperList;
    private FirebaseAuth signOutAuth;
    private NotificationsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notifications, container, false);

        super.onCreate(savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbarMenu);
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        signOutAuth = FirebaseAuth.getInstance();



        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);


        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);


        wrapperList = new ArrayList<>();
        FirebaseUser currentUser = signOutAuth.getCurrentUser();
        String userId = currentUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("notification").orderByChild("userId").equalTo(userId);

        query.addValueEventListener(valueEventListener);

        return  view;


    }



    ValueEventListener valueEventListener = new ValueEventListener() {
        /**
         * gets the notifications from the database
         * @param dataSnapshot wich the DB information
         */
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists())
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NotificationClass noti = snapshot.getValue(NotificationClass.class);
                    noti.print();
                    String k = snapshot.getKey();
                    WrapperNotification wn = new WrapperNotification(noti , k);
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

    /**
     * initializes the notification cards
     * @param listi list of notifications
     */
    private void initializeAdapter(List<NotificationClass> listi){
        NotificationsAdapter adapter = new NotificationsAdapter(listi ,  this , this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * inflates the notification cards
     * @param menu hamburger menu
     * @return true if it can be created
     */

    public boolean onCreateOptionsMenu( Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate( R.menu.hamburger_menu, menu);
        return true;
    }

    public void onBackPressed() {
    }

    /**
     * options for the hamburger menu
     * @param item hamburger menu
     * @return true if it can be creted
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId())
        {
            case R.id.first_item:
                Intent intent = new Intent( getContext(), CreateOpportunity.class );
                startActivity(intent);
                return true;
            case R.id.second_item:
                Intent intent1 = new Intent( getContext(), Notifications.class );
                startActivity(intent1);
                return true;
            case R.id.third_item:
                signOutAuth.signOut();
                Intent intent2 = new Intent( getContext(), LogIn.class );
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void OnDismiss(int position){

        deleteNotification(wrapperList.get(position).getKey());
        Toast toast=Toast.makeText(getContext(), "Me voy a eliminar",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
        notifications.remove(position);
        wrapperList.remove(position);
        initializeAdapter(notifications);


    }

    public void deleteNotification(String notifID){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("notification").child(notifID).setValue(null);

    }



    /**
     * listener for the "View" button has a different does different things depending on the type
     * @param position of the notifications in the arraylist
     */
    @Override
    public void OnSeeClick(int position){

        updateSeen(wrapperList.get(position).getKey());

        if(notifications.get(position).getType().equals("Match")){
            Toast toast=Toast.makeText(getContext(), "Soy un match",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            //TODO: intent to opportunity detatils activity (p21)
        }
        else if(notifications.get(position).getType().equals("Recommendation")){
            //TODO: intent to profile public activity (p16)
            Toast toast=Toast.makeText(getContext(), "soy una recomendacion",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }
        else if(notifications.get(position).getType().equals("Accepted")) {
            //TODO: intent to pop up with publisher contact info
            Intent intent = new Intent(getContext(), PopUpContactInfo.class);
            Toast toast = Toast.makeText(getContext(), "soy un accepted", Toast.LENGTH_SHORT);
            toast.setMargin(50, 50);
            toast.show();
            startActivity(intent);
        }
    }

    /**
     * sets the "seen" value in the database to true
     * @param notifID
     */
    private void updateSeen( String notifID ){

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("notification").child(notifID).child("seen").setValue(true);


    }


    /**
     * generates a notification arraylist based on the wrapper arraylist
     * @param wnl wrapper notification arraylist
     * @return NotificationClass arraylist
     */
    private ArrayList<NotificationClass> generateNotificationsList(ArrayList<WrapperNotification> wnl){
        ArrayList<NotificationClass> notifs = new ArrayList<NotificationClass>();
        for(int i = 0 ; i < wnl.size() ; ++i){
            notifs.add(wnl.get(i).getNot());
        }
        return notifs;
    }
}


