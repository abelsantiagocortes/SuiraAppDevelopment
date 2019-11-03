package com.development.SuiraApp.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.MyViewHolder> {
    private List<Integer> imgs;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        CircularImageView userPhoto;

        public MyViewHolder(CircularImageView v) {
            super(v);
            userPhoto =  v.findViewById(R.id.recommendedPhoto);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecommendedAdapter(List<Integer> imgs) {
        this.imgs = imgs;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecommendedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        CircularImageView v = (CircularImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommended, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.userPhoto.setImageResource(imgs.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return imgs.size();
    }
}