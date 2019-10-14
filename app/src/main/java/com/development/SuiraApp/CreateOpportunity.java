package com.development.SuiraApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateOpportunity extends AppCompatActivity {

    Button btnNext;

    GridLayout gridLayout2;
    TextView txt_showselected2;
    DatabaseReference dbTags;

    List<String> tagsFire;
    int canttags =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_opportunity);

        //Se infla el gridlayout y el textview de los tags
        gridLayout2 = (GridLayout) findViewById(R.id.grid_layout2);
        txt_showselected2 = (TextView) findViewById(R.id.txt_showselected2);


        FirebaseDatabase dbSuira = FirebaseDatabase.getInstance();
        dbTags =  dbSuira.getReference("tag");

        tagsFire = new ArrayList<String>();

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
                gridLayout2.removeAllViews();
                tagComponents();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnNext=(Button)findViewById(R.id.button3);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), CreateOpportunity2.class);
                startActivity(intent);
            }
        });

    }


    void tagComponents()
    {

        //Se crea la cantidad de botones necesarios para representar los tags
        for (int i = 0; i < tagsFire.size(); i++) {
            //Reset Grid Layout

            // Cantidad de hijos del GridLayout.
            int childCount = gridLayout2.getChildCount();

            // Get application context.
            Context context = getApplicationContext();
            // Crea cada boton en el contexto de la Actividad
            final Button tags = new Button(context);

            //Tamaño para los botones de tags
            final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            int pixels = (int) (104 * scale + 0.5f);

            //Le pone el texto. background, el tipo de texto y el tamaño
            tags.setText(tagsFire.get(i));
            tags.setBackgroundResource(R.drawable.btn_tag);
            tags.setTextAppearance(getApplicationContext(), R.style.btn_tag);
            tags.setWidth(pixels);

            //Click listener de todos los botones tags
            tags.setOnClickListener(new View.OnClickListener() {
                //Mira si esta clickeado o no
                Boolean click = false;
                Boolean first = false;

                @Override
                public void onClick(View v) {

                    //Si no esta clickeado cambia el estilo y lo pone en el color adecuado
                    if (click == false) {
                        //Se asegura de que no vayan mas de 5 tags
                        if(canttags<5){
                            tags.setBackgroundResource(R.drawable.btn_high_action);
                            click = true;
                            if (txt_showselected2.getText().toString().equals(".")) {
                                txt_showselected2.setText(tags.getText().toString());
                                canttags++;
                            } else {
                                txt_showselected2.setText(txt_showselected2.getText().toString() + "  " + tags.getText().toString());
                                canttags++;
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Maximo 5 Tags. ",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //Si el boton ya a sido clickeado cambia el estilo y borra lo necesario de los tags del usuario
                        if (first == false) {
                            String withTag = txt_showselected2.getText().toString();

                            String withoutTag = withTag.replace(tags.getText().toString(), "");
                            txt_showselected2.setText(withoutTag);
                            first = true;
                            canttags--;

                        } else {
                            String withTag = txt_showselected2.getText().toString();

                            String withoutTag = withTag.replace("  " + tags.getText().toString(), "");
                            txt_showselected2.setText(withoutTag);
                            canttags--;
                        }
                        tags.setBackgroundResource(R.drawable.btn_tag);

                        click = false;

                    }

                }
            });

            // Se añade el boton al gridLayout
            gridLayout2.addView(tags, childCount);
        }
    }
}
