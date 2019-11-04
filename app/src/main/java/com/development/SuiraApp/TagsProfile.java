package com.development.SuiraApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;
import java.util.List;

public class TagsProfile extends AppCompatActivity {

    GridLayout gridLayout;
    TextView txt_description;
    DatabaseReference dbUsers;
    DatabaseReference dbTags;
    List<String> tagsFire= Arrays.asList(new String[]{"holidasdasdasdasdassds", "como", "vas","holi","bb"});
    FirebaseAuth registerAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_profile);
        //Se infla el gridlayout y el textview de los tags
        gridLayout = (GridLayout) findViewById(R.id.grid_layoutProf);
        txt_description = (TextView) findViewById(R.id.txt_description);
        tagComponents();

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
