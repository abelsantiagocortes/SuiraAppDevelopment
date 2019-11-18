package com.development.SuiraApp.Adapters.Opportunities;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Model.ApplicationClass;

import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.List;

public class ApplicationAdapter  extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder>{

    List<ApplicationClass> apis;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameOppo;
        TextView nameProfile;
        TextView descripOppo;
        TextView locationOppo;
        TextView dateOppo;
        TextView moneyOppo;
        CircularImageView imgOpp;

        public MyViewHolder(View v) {
            super(v);
            nameOppo = v.findViewById(R.id.nameOppo);
            nameProfile = v.findViewById(R.id.nameProfile);
            imgOpp = v.findViewById(R.id.fotoOppo);
            descripOppo = v.findViewById(R.id.descriptionOppo);
            locationOppo = v.findViewById(R.id.textView21);
            dateOppo = v.findViewById(R.id.textView22);
            moneyOppo = v.findViewById(R.id.textView23);

        }

    }

    public ApplicationAdapter(List<ApplicationClass> apis) {
        this.apis = apis;
    }

    @Override
    public ApplicationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aplicaciones, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ApplicationAdapter.MyViewHolder holder, int position) {

        final ApplicationAdapter.MyViewHolder x = holder;
        x.nameOppo.setText(apis.get(position).getOpp().getName());
        x.descripOppo.setText(apis.get(position).getOpp().getDescription());
        x.moneyOppo.setText(String.valueOf(apis.get(position).getOpp().getBudget()));
        x.dateOppo.setText(apis.get(position).getOpp().getEndDate());
        x.nameProfile.setText(apis.get(position).getNombre());

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child("images/userClient/" + apis.get(position).getOpp().getPublisherId()).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bMap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                x.imgOpp.setImageBitmap(bMap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("No se pudo sacar la foto");
            }
        });


    }


    @Override
    public int getItemCount() {
        return apis.size();
    }



}
