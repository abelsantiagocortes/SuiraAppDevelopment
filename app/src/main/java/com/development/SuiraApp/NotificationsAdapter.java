package com.development.SuiraApp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotifViewHolder> {

    public static class NotifViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView oppoName;
        TextView oppoDescription;
        CircularImageView userPhoto;

        NotifViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardi);
            oppoName = (TextView)itemView.findViewById(R.id.textView3);
            oppoDescription = (TextView)itemView.findViewById(R.id.textView2);
            userPhoto = (CircularImageView) itemView.findViewById(R.id.foto);
        }
    }

    List<NotificationClass> notifs;
    String suiraPurple = "#4B2C70";
    NotificationsAdapter(List<NotificationClass> notifs){
        this.notifs = notifs;
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NotifViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifications, viewGroup, false);


        NotifViewHolder pvh = new NotifViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(NotifViewHolder notifViewHolder, int i) {

        notifViewHolder.oppoName.setText(notifs.get(i).name);
        notifViewHolder.oppoDescription.setText(notifs.get(i).description);
        if(notifs.get(i).getSeen()){
            //System.out.println(notifs.get(i).getName() +" es true");
            notifViewHolder.oppoDescription.setTextColor(Color.parseColor(suiraPurple));

        }
    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }
}