package com.development.SuiraApp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.development.SuiraApp.Adapters.Opportunities.RecommendedAdapter;
import com.development.SuiraApp.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileTest extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_test);

        recyclerView = (RecyclerView) findViewById(R.id.rcyRecommended);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        int x = R.drawable.photographer;
        List<Integer> imgs = new ArrayList<>();
        for (int c = 0; c < 20; c++)
        {
            imgs.add(R.drawable.photographer);
            imgs.add(R.drawable.paint);
            imgs.add(R.drawable.fashion);
            imgs.add(R.drawable.singer);
        }

        // specify an adapter (see also next example)
        mAdapter = new RecommendedAdapter(imgs);
        recyclerView.setAdapter(mAdapter);
    }
}
