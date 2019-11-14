package com.development.SuiraApp.Adapters;

import android.graphics.Color;
import android.net.Uri;
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


    public static class NotifViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView oppoName;
        TextView oppoDescription;
        TextView oppoTitle;
        CircularImageView userPhoto;
        Button viewButton;
        int position;
        OnSeeListener myListener;


        /**
         * creates a notification card
         * @param itemView the view that will contaion the notification card
         * @param onSeeListener listener that the button will implement
         */
        NotifViewHolder(View itemView , OnSeeListener onSeeListener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardi);
            oppoName = (TextView)itemView.findViewById(R.id.textView3);
            oppoTitle = (TextView)itemView.findViewById(R.id.textView2);
            oppoDescription = (TextView)itemView.findViewById(R.id.textViewDescription);
            userPhoto = (CircularImageView) itemView.findViewById(R.id.foto);
            viewButton = (Button) itemView.findViewById(R.id.button3);
            viewButton.setOnClickListener(this);
            this.myListener = onSeeListener;

        }

        /**
         * Listener for the "View" button in a notification card
         * @param view the view wich will have the listener
         */
        @Override
        public void onClick(View view) {
            myListener.OnSeeClick(getAdapterPosition());
        }
    }


    public NotificationsAdapter(List<NotificationClass> notifs, OnSeeListener onSeeListener){
        this.notifs = notifs;
        this.onSeeListener = onSeeListener;
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NotifViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifications, viewGroup, false);


        NotifViewHolder pvh = new NotifViewHolder(v , onSeeListener);
        return pvh;
    }

    /**
     * Sets the Notification information to the text view depending on the type of notification
     * @param notifViewHolder
     * @param i the position of each notification in the arraylist
     */
    @Override
    public void onBindViewHolder(final NotifViewHolder notifViewHolder, int i) {

        //Bitmap  mBitmap = Media.getBitmap(this.getContentResolver(), uri);
        //notifViewHolder.userPhoto.setImageBitmap() ;

        //StorageReference storageRef = FirebaseStorage.getInstance().getReference();


        if(notifs.get(i).getType().equals("Match")) {
            notifViewHolder.oppoTitle.setText("Match: " + notifs.get(i).getName());
            notifViewHolder.oppoDescription.setText( "");


        }
        else if(notifs.get(i).getType().equals("Accepted")){

            notifViewHolder.oppoTitle.setText("Accepted Aplication!");
            notifViewHolder.oppoDescription.setText( notifs.get(i).getName());
        }
        else if(notifs.get(i).getType().equals("Recommendation")){
            notifViewHolder.oppoTitle.setText("New Recommendation");
            notifViewHolder.oppoDescription.setText( notifs.get(i).getPublisherName() +" has recommended you, visit their profile");
        }
        notifViewHolder.oppoName.setText(notifs.get(i).getPublisherName());
        if (notifs.get(i).getSeen() == false) {
            //Hay que vambiar el borde a suiraPurple, pero no se como :)
            notifViewHolder.oppoDescription.setTextColor(Color.parseColor(suiraPurple));
        }
    }

    public interface  OnSeeListener{
        void OnSeeClick(int position);
    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }
}