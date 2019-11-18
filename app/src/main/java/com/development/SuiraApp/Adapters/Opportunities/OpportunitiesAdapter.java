package com.development.SuiraApp.Adapters.Opportunities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Model.OpportunityClass;
import com.development.SuiraApp.R;

import java.util.List;

public class OpportunitiesAdapter extends RecyclerView.Adapter<OpportunitiesAdapter.MyViewHolder> {

    List<OpportunityClass> opps;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardi;
        TextView titleOppo;

        public MyViewHolder(View v) {
            super(v);
            cardi= v.findViewById(R.id.cardiOpps);
            titleOppo = v.findViewById(R.id.txtOppo);

        }
    }

    public OpportunitiesAdapter(List<OpportunityClass> opps) {
        this.opps = opps;
    }

    @Override
    public OpportunitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opportunities, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(OpportunitiesAdapter.MyViewHolder holder, int position) {

        holder.titleOppo.setText(opps.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return opps.size();
    }

}
