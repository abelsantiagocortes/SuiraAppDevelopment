package com.development.SuiraApp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.development.SuiraApp.R;

public class PopUpContactInfo extends AppCompatActivity {

    TextView address;
    TextView mail;
    TextView phone;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme( R.style.pop_up);
        setContentView(R.layout.activity_pop_up_contact_info);

        address = (TextView) findViewById( R.id.popContentAddress );
        mail = (TextView) findViewById( R.id.popContentEmail );
        phone = (TextView) findViewById( R.id.popContentPhone );
        boton = ( Button ) findViewById( R.id.popButton );

        //TODO: Info quedama, extraer del usuario que acepto
        address.setText(  "Cr 58 #125b - 57"  );
        mail.setText( "example@example.com" ) ;
        phone.setText( "+571 3177963053" ) ;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(  ActionMenuView.LayoutParams.WRAP_CONTENT, ActionMenuView.LayoutParams.WRAP_CONTENT );

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void senderNew( String sendActivity )
    {
        System.out.println(sendActivity);
        String activityToStart = "com.development.SuiraApp." + sendActivity;
        try {
            Class<?> aac = Class.forName(activityToStart);
            Intent intent = new Intent(this, aac);
            startActivity(intent);
        } catch (ClassNotFoundException ignored) {
        }
    }
}
