package com.development.SuiraApp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.development.SuiraApp.Model.TagClass;
import com.development.SuiraApp.Model.UserClientClass;
import com.development.SuiraApp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterTags extends AppCompatActivity {

    private String[] items = new String[]{ "Not that good","Average" ,"Above average" ,"Advanced"};
    private FirebaseAuth registerAuth;
    private DatabaseReference dbUsers;
    private Spinner[] dropdown= {null , null , null , null , null};
    private Button[] tagName = {null , null , null , null , null};
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tags);

        dropdown[0] = findViewById(R.id.spinner);
        dropdown[1] = findViewById(R.id.spinner2);
        dropdown[2] = findViewById(R.id.spinner3);
        dropdown[3] = findViewById(R.id.spinner4);
        dropdown[4] = findViewById(R.id.spinner5);

        tagName[0] = findViewById(R.id.buttonTag1);
        tagName[1] = findViewById(R.id.buttonTag2);
        tagName[2] = findViewById(R.id.buttonTag3);
        tagName[3] = findViewById(R.id.buttonTag4);
        tagName[4] = findViewById(R.id.buttonTag5);
        btnEnviar = findViewById(R.id.nextR);


        //recibe el usuario de la actividad anterior

        final List<String> tagNames  = (List<String>) getIntent().getSerializableExtra("userTags");

        setTags(tagNames);

        registerAuth = FirebaseAuth.getInstance();
        dbUsers = FirebaseDatabase.getInstance().getReference("userClient");
        FirebaseUser currentUser = registerAuth.getCurrentUser();
        final String userId = currentUser.getUid();


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            /**
             * inserta el usuario en la base de datos con los valores de rating de los tags
             * @param view
             */
            @Override
            public void onClick(View view) {


                Bundle bund = new Bundle();

                String msn = "Thanks for Using Suira\n \n \n Suira is Working To Find What You Need";
                String btnMsn = "Ok";
                String activityName = "LogIn";

                Intent intentP= new Intent(getApplicationContext(), PopUp.class);

                bund.putString("mensaje", msn);
                bund.putString("contenidoBoton", btnMsn);
                bund.putString("sender", activityName );
                intentP.putExtras(bund);

                startActivity(intentP);
                List<Double> tagVals = getTagValues(tagNames.size());
                //mete al usuario a la BD
                UserClientClass user = (UserClientClass) getIntent().getSerializableExtra("userObject");
                user.setTag(generateTags(tagNames , tagVals));

                dbUsers.child(userId).setValue(user);


                Intent intentAdicional= new Intent(getApplicationContext(), AditionalInfo.class);
                intentAdicional.putExtra("userObject",  user );
                intentAdicional.putExtra("userTags" , (Serializable) tagNames);
                startActivity(intentAdicional);
                finish();
            }


        });



    }

    /**
     * retorna la lista con los valores del rating dependiendo de lo que el usuario escoge en el spinner para cada tag
     * @param size numero de tags que escoge el usuario
     * @return Lista con el rating (numerico) de cada tag
     */
    private List<Double> getTagValues(int size) {
        List<Double> res = new ArrayList<>();
        for(int i = 0 ; i<size ; ++i){
            double val = dropdown[i].getSelectedItemPosition() + 2;
            if(dropdown[i].getSelectedItemPosition() == 3){
                val = 4.5;
            }
            res.add(val);
        }
        return res;

    }


    /**
     *
     * @param tagNames lista con los nombrres de los tags
     * @param tagValues lista con los valores de los tags
     * @return mapa con el nombre, y el rating de cada tag
     */
    private Map<String , TagClass> generateTags(List<String> tagNames , List<Double> tagValues){
        Map<String ,TagClass> res = new HashMap<>();
        for(int i =0 ; i<tagNames.size() ; ++i){
            System.out.println(i);
            res.put(tagNames.get(i) , new TagClass( tagValues.get(i), 1));
        }

        return res;
    }


    /**
     * asigna los nombres de los tags a los labels necesarios
     * @param list lista con los nombres de los tags escogidos por el usuario en la pantalla anterior
     */
    private void setTags(List<String> list){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        int i;
        for( i = 0 ; i < list.size() ; ++i){
            tagName[i].setText(list.get(i));
            dropdown[i].setAdapter(adapter);
        }
        for( ; i<5 ; ++i){
            tagName[i].setVisibility(View.GONE);
            dropdown[i].setVisibility(View.GONE);
        }
    }

}
