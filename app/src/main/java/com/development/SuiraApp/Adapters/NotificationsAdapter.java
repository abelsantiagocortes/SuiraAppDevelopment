package com.development.SuiraApp.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Model.NotificationClass;
import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotifViewHolder> {


    List<NotificationClass> notifs;
    String suiraPurple = "#4B2C70";
    OnSeeListener onSeeListener;
    OnDismissListener onDismissListener;


    public static class NotifViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView oppoName;
        TextView oppoDescription;
        TextView oppoTitle;
        CircularImageView userPhoto;
        Button viewButton;
        Button dismissButton;
        int position;
        OnSeeListener myListener;
        OnDismissListener onDismissListener;


        /**
         * creates a notification card
         * @param itemView the view that will contaion the notification card
         * @param onSeeListener listener that the button will implement
         */
        NotifViewHolder(View itemView , OnSeeListener onSeeListener,  OnDismissListener onDismissListener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardi);
            oppoDescription = (TextView)itemView.findViewById(R.id.textView3);
            oppoTitle = (TextView)itemView.findViewById(R.id.textView2);
            userPhoto = (CircularImageView) itemView.findViewById(R.id.img_profile);
            viewButton = (Button) itemView.findViewById(R.id.button3);

            viewButton.setOnClickListener(this);
            this.myListener = onSeeListener;

            dismissButton = (Button) itemView.findViewById(R.id.button2);
            dismissButton.setOnClickListener(this);
            this.onDismissListener = onDismissListener;
        }

        /**
         * Listener for the "View" button in a notification card
         * @param view the view wich will have the listener
         */
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button3) {
                myListener.OnSeeClick(getAdapterPosition());
            } else {
                onDismissListener.OnDismiss(getAdapterPosition());

            }
        }
    }


    public NotificationsAdapter(List<NotificationClass> notifs, OnSeeListener onSeeListener , OnDismissListener onDismissListener){
        this.notifs = notifs;
        this.onSeeListener = onSeeListener;
        this.onDismissListener = onDismissListener;
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NotifViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifications, viewGroup, false);


        NotifViewHolder pvh = new NotifViewHolder(v , onSeeListener , onDismissListener);
        return pvh;
    }

    /**
     * Sets the Notification information to the text view depending on the type of notification
     * @param notifViewHolder
     * @param i the position of each notification in the arraylist
     */
    @Override
    public void onBindViewHolder(final NotifViewHolder notifViewHolder, int i) {

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child("images/userClient/" + notifs.get(i).getPublisherId()).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bMap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                notifViewHolder.userPhoto.setImageBitmap(bMap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("No se pudo sacar la foto");
            }
        });
        if(notifs.get(i).getType().equals("Match")) {
            notifViewHolder.oppoTitle.setText( notifs.get(i).getName());
            notifViewHolder.oppoDescription.setText( notifs.get(i).getPublisherName());
        }
        else if(notifs.get(i).getType().equals("Accepted")){

            notifViewHolder.oppoTitle.setText("Accepted Aplication!");
            notifViewHolder.oppoDescription.setText( notifs.get(i).getName());
        }
        else if(notifs.get(i).getType().equals("Recommendation")){
            notifViewHolder.oppoTitle.setText("New Recommendation");
            notifViewHolder.oppoDescription.setText( notifs.get(i).getPublisherName() +" has recommended you, visit their profile");
        }

        if (notifs.get(i).getSeen() == false) {
            //Hay que vambiar el borde a suiraPurple, pero no se como :)
            notifViewHolder.oppoTitle.setTextColor(Color.parseColor(suiraPurple));
        }
    }

    public interface  OnSeeListener{
        void OnSeeClick(int position);

    }

    public interface  OnDismissListener{
        void OnDismiss(int position);

    }
    @Override
    public int getItemCount() {
        return notifs.size();
    }
}