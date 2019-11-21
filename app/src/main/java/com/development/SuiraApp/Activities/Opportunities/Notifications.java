package com.development.SuiraApp.Activities.Opportunities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.development.SuiraApp.Model.UserClientClass;
import com.development.SuiraApp.PopUps.PopUpContactInfo;
import com.development.SuiraApp.Model.NotificationClass;
import com.development.SuiraApp.Adapters.Opportunities.NotificationsAdapter;
import com.development.SuiraApp.R;
import com.development.SuiraApp.Adapters.Opportunities.WrapperNotification;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notifications extends Fragment implements NotificationsAdapter.OnSeeListener , NotificationsAdapter.OnDismissListener {

    private RecyclerView recyclerView;
    public List<NotificationClass> notifications;
    public ArrayList<WrapperNotification> wrapperList;
    private FirebaseAuth signOutAuth;
    private NotificationsAdapter adapter;
    private Map<String , byte[]> fotos = new HashMap<>();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notifications, container, false);

        super.onCreate(savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbarMenu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        signOutAuth = FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);



        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);


        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);


        wrapperList = new ArrayList<>();
        notifications = new ArrayList<>();
        FirebaseUser currentUser = signOutAuth.getCurrentUser();
        String userId = currentUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("notification").orderByChild("userId").equalTo(userId);
       // query.addListenerForSingleValueEvent(listener2);
        query.addChildEventListener(listenerChanges);


        return  view;
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
                    final NotificationClass noti = snapshot.getValue(NotificationClass.class);
                    noti.print();
                    String k = snapshot.getKey();
                    WrapperNotification wn = new WrapperNotification(noti, k);
                    wrapperList.add(wn);

                    storageRef.child("images/userClient/" + noti.getPublisherId()).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            System.out.println("Estoy pusheando a " + noti.getPublisherId());
                            fotos.put(noti.getPublisherId() , bytes);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            System.out.println("No se pudo sacar la foto");
                        }
                    });



                }
            }

            Collections.sort(wrapperList);

            notifications = generateNotificationsList(wrapperList);
            initializeAdapter();
            System.out.println("Este es listener 2");

        }

            @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    /**
     * Listener for when the data changes
     */
    ChildEventListener listenerChanges =  new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                final NotificationClass noti = dataSnapshot.getValue(NotificationClass.class);
               System.out.println("Agregaron la notificacion " + dataSnapshot.getKey());
               //agrego la notificacion al arrayList y vuelvo a iniciar el recycler view
                storageRef.child("images/userClient/" + noti.getPublisherId()).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        fotos.put(noti.getPublisherId() , bytes);
                        initializeAdapter();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        System.out.println("No se pudo sacar la foto");
                    }
                });

                WrapperNotification wn = new WrapperNotification(noti, dataSnapshot.getKey());
                wrapperList.add(0,wn);
                notifications.add(0 , dataSnapshot.getValue(NotificationClass.class));
                initializeAdapter();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println("Cambiaronla notificacion " + dataSnapshot.getKey());
                NotificationClass noti = dataSnapshot.getValue(NotificationClass.class);

                for(int i = 0 ; i< wrapperList.size() ; ++i) {
                    if (wrapperList.get(i).key.equals(dataSnapshot.getKey())) {
                        wrapperList.get(i).not = noti;
                        notifications.set(i, noti);
                    }
                }
                initializeAdapter();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("quitaron  la notificacion " + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println("Movieron  la notificacion " + dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

    /**
     * initializes the notification cards
     */
    private void initializeAdapter(){
        NotificationsAdapter adapter = new NotificationsAdapter(notifications ,  this , this , fotos);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void OnDismiss(int position){

        deleteNotification(wrapperList.get(position).getKey());
        Toast toast=Toast.makeText(getContext(), "Me voy a eliminar",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
        notifications.remove(position);
        wrapperList.remove(position);
        initializeAdapter();


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
            Bundle temp = new Bundle();
            String opportunityId = notifications.get(position).getOpportunityId();
            Intent intent = new Intent(getContext(), OpportunityInfo.class);
            temp.putString("OppId", opportunityId);
            intent.putExtras(temp);
            startActivity(intent);
        }
        else if(notifications.get(position).getType().equals("Recommendation")){
            //TODO: intent to profile public activity (p16)
            Toast toast=Toast.makeText(getContext(), "soy una recomendacion",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }
        else if(notifications.get(position).getType().equals("Accepted")) {
            final Toast toast=Toast.makeText(getContext(), "Error",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);

            //sacar la informacion del usuario para mandarla al intent
            String userId = notifications.get(position).getPublisherId();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference ref = database.getReference("userClient");
            ref.orderByKey().equalTo(userId).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    toast.show();
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    toast.show();
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    toast.show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    toast.show();
                }

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    UserClientClass usuario= dataSnapshot.getValue(UserClientClass.class);

                    Intent intent = new Intent(getContext(), PopUpContactInfo.class);
                    Bundle temp = new Bundle();
                    temp.putString("direccion",  usuario.getLocation());
                    temp.putString("telefono",  usuario.getPhone());

                    intent.putExtras(temp);
                    startActivity(intent);
                }

            });




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


