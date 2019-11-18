package com.development.SuiraApp.Adapters;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Model.ApplicationClass;
import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Map;

public class AplicantsAdapter extends RecyclerView.Adapter<AplicantsAdapter.ApliViewHolder> {

    List<ApplicationClass> apps;
    OnAcceptListener onAcceptListener;
    OnGoListener onGoListener;
    Map<String , byte[]> fotos;
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();



    public static class ApliViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView porcentaje;
        CardView cv;
        TextView aplicantName;
        CircularImageView userPhoto;
        Button acceptButton;
        Button goToButton;
        OnAcceptListener onAcceptListenerVH;
        OnGoListener onGoListenerVH;

        /**
         * creates the view that will display the aplicant
         * @param itemView the view
         * @param onGoListener functino that reponds to the button "Go to profile"
         * @param onAcceptListener function that responds to the button "Accept"
         */
        ApliViewHolder(View itemView , OnGoListener onGoListener, OnAcceptListener onAcceptListener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardsApplicant);
            userPhoto = (CircularImageView) itemView.findViewById(R.id.img_profile);
            acceptButton = (Button) itemView.findViewById(R.id.button3);
            aplicantName = itemView.findViewById(R.id.aplicantName);
            porcentaje = itemView.findViewById(R.id.porcentaje);
            acceptButton.setOnClickListener(this);
            this.onAcceptListenerVH = onAcceptListener;

            goToButton = (Button) itemView.findViewById(R.id.button2);
            goToButton.setOnClickListener(this);
            this.onGoListenerVH = onGoListener;
        }

        /**
         * Listener for the "View" button in a notification card
         * @param view the view wich will have the listener
         */
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button3) {
                onAcceptListenerVH.OnAccept(getAdapterPosition());
            } else {
                onGoListenerVH.OnGo(getAdapterPosition());

            }
        }
    }


    public AplicantsAdapter(List<ApplicationClass> apps, AplicantsAdapter.OnAcceptListener acceptListener, AplicantsAdapter.OnGoListener goListener, Map<String , byte[]> fotitos){
        this.apps = apps;
        this.fotos = fotitos;
        this.onAcceptListener = acceptListener;
        this.onGoListener = goListener;

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    //abel es gay

    @Override
    public ApliViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_aplicant, viewGroup, false);


        ApliViewHolder pvh = new ApliViewHolder(v , onGoListener , onAcceptListener);
        return pvh;
    }


    /**
     * Sets the Notification information to the text view depending on the type of notification
     * @param notifViewHolder
     * @param i the position of each notification in the arraylist
     */
    @Override
    public void onBindViewHolder(final ApliViewHolder notifViewHolder, int i) {
        final String applicant= apps.get(i).getApplicantId();
        System.out.println("*/---------------------------------------------------------/*");
        System.out.println("id: " + applicant);
        notifViewHolder.porcentaje.setText(Double.toString(apps.get(i).getPorcentaje()) );
        notifViewHolder.aplicantName.setText(apps.get(i).getNombre());
        Query query = FirebaseDatabase.getInstance().getReference("userClient").orderByKey().equalTo(applicant);

        ValueEventListener listener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    System.out.println("Estoy en el listener");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //pone el nombre

                        storageRef.child("images/userClient/" + applicant).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {


                            @Override
                            public void onSuccess(byte[] bytes) {
                                System.out.println("Estoy sacando la foto");
                                Bitmap bMap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                notifViewHolder.userPhoto.setImageBitmap(bMap);
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                System.out.println("No se pudo sacar la foto");
                            }
                        });
                    }
                }
                else{
                    System.out.println("no saco nada la query");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        query.addValueEventListener(listener);

    }


        public interface  OnAcceptListener{
        void OnAccept(int position);
    }

    public interface  OnGoListener{
        void OnGo(int position);

    }
    @Override
    public int getItemCount() {
        return apps.size();
    }
}