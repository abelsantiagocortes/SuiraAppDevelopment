package com.development.SuiraApp.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Model.NotificationClass;
import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotifViewHolder> {


    List<NotificationClass> notifs;
    String suiraPurple = "#4B2C70";
    OnSeeListener onSeeListener;


    public static class NotifViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView oppoName;
        TextView oppoDescription;
        CircularImageView userPhoto;
        Button viewButton;
        int position;
        OnSeeListener myListener;


        NotifViewHolder(View itemView , OnSeeListener onSeeListener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardi);
            oppoName = (TextView)itemView.findViewById(R.id.textView3);
            oppoDescription = (TextView)itemView.findViewById(R.id.textView2);
            userPhoto = (CircularImageView) itemView.findViewById(R.id.foto);
            viewButton = (Button) itemView.findViewById(R.id.button3);
            viewButton.setOnClickListener(this);
            this.myListener = onSeeListener;

        }

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

    @Override
    public void onBindViewHolder(final NotifViewHolder notifViewHolder, int i) {

        notifViewHolder.oppoName.setText(notifs.get(i).name);
        notifViewHolder.oppoDescription.setText(notifs.get(i).description);
        if(notifs.get(i).getSeen() == false){
            //System.out.println(notifs.get(i).getName() +" es true");
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