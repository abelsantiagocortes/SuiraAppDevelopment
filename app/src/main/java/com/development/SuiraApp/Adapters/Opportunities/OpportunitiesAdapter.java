package com.development.SuiraApp.Adapters.Opportunities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Activities.AplicantesOportunidad;
import com.development.SuiraApp.Model.OpportunityClass;
import com.development.SuiraApp.R;

import java.io.Serializable;
import java.util.List;

public class OpportunitiesAdapter extends RecyclerView.Adapter<OpportunitiesAdapter.MyViewHolder> {

    List<OpportunityClass> opps;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardi;
        TextView titleOppo;
        Button aplicantes;

        public MyViewHolder(View v) {
            super(v);
            cardi= v.findViewById(R.id.cardiOpps);
            titleOppo = v.findViewById(R.id.txtOppo);
            aplicantes = v.findViewById(R.id.btnAplicaciones);

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
    public void onBindViewHolder(OpportunitiesAdapter.MyViewHolder holder, final int position) {

        final OpportunitiesAdapter.MyViewHolder h=holder;
        holder.titleOppo.setText(opps.get(position).getName());
        holder.aplicantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AplicantesOportunidad.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nombre",opps.get(position).getKey());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return opps.size();
    }

}
