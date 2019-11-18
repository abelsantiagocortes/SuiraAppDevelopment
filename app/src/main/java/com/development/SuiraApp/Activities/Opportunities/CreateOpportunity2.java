package com.development.SuiraApp.Activities.Opportunities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.development.SuiraApp.Activities.Authentication.LogIn;
import com.development.SuiraApp.Model.OpportunityClass;
import com.development.SuiraApp.PopUps.PopUp;
import com.development.SuiraApp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateOpportunity2 extends AppCompatActivity {

    private Button botonsito;
    private DatabaseReference dbOpps;
    private FirebaseDatabase dbSuira;
    private EditText budget;
    private EditText fecha;
    private EditText location;
    private final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_opportunity_2);


        dbSuira = FirebaseDatabase.getInstance();
        dbOpps = dbSuira.getReference("opportunities");

        fecha = findViewById(R.id.eDate);
        location = findViewById(R.id.eLocation);
        budget = findViewById(R.id.eBudget);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            /**
             *Obtiene el día, mes y año actual para el calendario
             * @param view
             * @param year
             * @param monthOfYear
             * @param dayOfMonth
             */
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateOpportunity2.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        botonsito=(Button)findViewById(R.id.button8);


        botonsito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Si todos estan llenos
                if(validate()){
                    OpportunityClass opp = (OpportunityClass) getIntent().getSerializableExtra("oppObject");
                    opp.setBudget(Double.parseDouble(budget.getText().toString()));
                    opp.setEndDate(fecha.getText().toString());
                    opp.setLocation(location.getText().toString());

                    Bundle bund = new Bundle();

                    String msn = "Thanks for Using Suira\n \n \n Suira is Working To Find What You Need";
                    String btnMsn = "Log In";
                    String activityName = "LogIn";

                    Intent intentP= new Intent(getApplicationContext(), PopUp.class);

                    bund.putString("mensaje", msn);
                    bund.putString("contenidoBoton", btnMsn);
                    bund.putString("sender", activityName );
                    intentP.putExtras(bund);
                    startActivity(intentP);

                    String key = dbOpps.push().getKey();
                    dbOpps.child(key).setValue(opp);

                    Intent intentLog = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(intentLog);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Faltan campos ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Valida que todos los campos están completos
     * @return estado
     */
    public boolean validate(){
        boolean valid = true;
        String fechaV = fecha.getText().toString();
        String locationV = location.getText().toString();
        String budgetV = budget.getText().toString();
        //Fecha
        if (TextUtils.isEmpty(fechaV)) {
            fecha.setError("Required.");
            valid = false;
        } else {
            fecha.setError(null);
        }
        //Localización
        if (TextUtils.isEmpty(locationV)) {
            location.setError("Required.");
            valid = false;
        } else {
            location.setError(null);
        }
        //Budget
        if (TextUtils.isEmpty(budgetV)) {
            budget.setError("Required.");
            valid = false;
        } else {
            budget.setError(null);
        }

        return valid;


    }
    /**
     * Gener el calendario en formato MM/dd/yy
     */
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        fecha.setText(sdf.format(myCalendar.getTime()));
    }



}
