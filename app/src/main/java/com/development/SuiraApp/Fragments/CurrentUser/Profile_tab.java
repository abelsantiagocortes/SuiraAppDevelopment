package com.development.SuiraApp.Fragments.CurrentUser;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.development.SuiraApp.Adapters.Opportunities.RecommendedAdapter;
import com.development.SuiraApp.Model.UserClientClass;
import com.development.SuiraApp.Adapters.MyViewPagerAdapter;
import com.development.SuiraApp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Profile_tab extends Fragment {

    private MyViewPagerAdapter adapter;
    CircularImageView img_profile;
    GridLayout gridLayout;
    TextView txt_description;
    TextView txt_nameProf;
    TextView txt_locationProf;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    DatabaseReference dbUsers;
    DatabaseReference dbTags;
    List<String> tagsFire/*= Arrays.asList(new String[]{"holidasdasdasdasdassds", "como", "vas","holi","bb"})*/;
    FirebaseAuth registerAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_profile, container, false);

        //Se infla el gridlayout y el textview de los tags
        gridLayout = (GridLayout) view.findViewById(R.id.grid_layoutProf);
        txt_description = (TextView) view.findViewById(R.id.txt_description);
        txt_nameProf = view.findViewById(R.id.txt_nameProf);
        img_profile = view.findViewById(R.id.img_profile);
        txt_locationProf = view.findViewById(R.id.txt_locationProf);
        FirebaseDatabase dbSuira = FirebaseDatabase.getInstance();
        dbUsers = dbSuira.getReference("userClient");
        registerAuth = FirebaseAuth.getInstance();
        tagsFire = new ArrayList<String>();
        txt_nameProf.setMovementMethod(new ScrollingMovementMethod());
        txt_description.setMovementMethod(new ScrollingMovementMethod());

        // SACA EL ID
        String id = registerAuth.getCurrentUser().getUid();

        Query query = FirebaseDatabase.getInstance().getReference("userClient").child(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Aca sacas el objeto
                UserClientClass post = dataSnapshot.getValue(UserClientClass.class);
                txt_nameProf.setText(post.getName()+" "+post.getLastName());
                txt_description.setText(post.getDescription());
                txt_locationProf.setText(post.getLocation());

                // Imprimimos el Map con un Iterador
                Iterator it = post.getTag().keySet().iterator();
                while(it.hasNext()){
                    String key = it.next().toString();
                    tagsFire.add(key);
                }
                tagComponents();;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child("images/userClient/" + id).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bMap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                img_profile.setImageBitmap(bMap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("No se pudo sacar la foto");
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.rcyRecommended);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
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

        return  view;
    }
    void tagComponents()
    {

        //Se crea la cantidad de botones necesarios para representar los tags
        for (int i = 0; i < tagsFire.size(); i++) {
            //Reset Grid Layout

            // Cantidad de hijos del GridLayout.
            int childCount = gridLayout.getChildCount();

            // Get application context.
            Context context = getContext();
            // Crea cada boton en el contexto de la Actividad
            final Button tags = new Button(context);
            //Tamaño para los botones de tags
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int pixelsH = (int) (35* scale + 0.5f);
            int pixelsW = (int) (90* scale + 0.5f);

            //Le pone el texto. background, el tipo de texto y el tamaño
            tags.setText(tagsFire.get(i));
            tags.setBackgroundResource(R.drawable.btn_high_action);
            tags.setTextAppearance(getContext(), R.style.btn_highAction);


            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams( ActionBar.LayoutParams.WRAP_CONTENT,pixelsH);
            tags.setLayoutParams(layoutParams);

            // Se añade el boton al gridLayout
            gridLayout.addView(tags, childCount);
        }
    }
}
/*
public class TagsProfile extends AppCompatActivity {

    GridLayout gridLayout;
    TextView txt_description;
    DatabaseReference dbUsers;
    DatabaseReference dbTags;
    List<String> tagsFire;
//= Arrays.asList(new String[]{"holidasdasdasdasdassds", "como", "vas","holi","bb"});
    FirebaseAuth registerAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_profile);
        //Se infla el gridlayout y el textview de los tags
        gridLayout = (GridLayout) findViewById(R.id.grid_layoutProf);
        txt_description = (TextView) findViewById(R.id.txt_description);

        FirebaseDatabase dbSuira = FirebaseDatabase.getInstance();
        dbUsers = dbSuira.getReference("userClient");
        registerAuth = FirebaseAuth.getInstance();
        tagsFire = new ArrayList<String>();


        // SACA EL ID
        String id = registerAuth.getCurrentUser().getUid().toString();



        // Read Tags Every Time is Updated
        dbTags.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tagsFire.clear();
                for(DataSnapshot tagSnapshot : dataSnapshot.getChildren())
                {
                    String itemTag = tagSnapshot.getValue().toString();
                    tagsFire.add(itemTag);

                }
                //Reset the GridLayouts
                gridLayout.removeAllViews();
                tagComponents();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    void tagComponents()
    {

        //Se crea la cantidad de botones necesarios para representar los tags
        for (int i = 0; i < tagsFire.size(); i++) {
            //Reset Grid Layout

            // Cantidad de hijos del GridLayout.
            int childCount = gridLayout.getChildCount();

            // Get application context.
            Context context = getApplicationContext();
            // Crea cada boton en el contexto de la Actividad
            final Button tags = new Button(context);
            //Tamaño para los botones de tags
            final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            int pixelsH = (int) (35* scale + 0.5f);
            int pixelsW = (int) (90* scale + 0.5f);

            //Le pone el texto. background, el tipo de texto y el tamaño
            tags.setText(tagsFire.get(i));
            tags.setBackgroundResource(R.drawable.btn_high_action);
            tags.setTextAppearance(getApplicationContext(), R.style.btn_highAction);


            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams( ActionBar.LayoutParams.WRAP_CONTENT,pixelsH);
            tags.setLayoutParams(layoutParams);

            // Se añade el boton al gridLayout
            gridLayout.addView(tags, childCount);
        }
    }
}

 */