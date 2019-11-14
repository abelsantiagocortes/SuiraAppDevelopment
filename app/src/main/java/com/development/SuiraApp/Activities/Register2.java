package com.development.SuiraApp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.development.SuiraApp.Model.TagClass;
import com.development.SuiraApp.R;
import com.development.SuiraApp.Model.UserClientClass;
import com.development.SuiraApp.permissions.PermissionIds;
import com.development.SuiraApp.permissions.PermissionsActions;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Register2 extends AppCompatActivity {


    GridLayout gridLayout;
    TextView txt_showselected;
    Button btnNotif;
    DatabaseReference dbUsers;
    DatabaseReference dbTags;
    StorageReference storageUserClients;
    List<String> tagsFire;
    FirebaseAuth registerAuth;
    int canttags =0;
    private static Uri imageUri = null;
    ImageView uploadImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        //Se infla el gridlayout y el textview de los tags
        gridLayout = (GridLayout) findViewById(R.id.grid_layout);
        txt_showselected = (TextView) findViewById(R.id.txt_showselected);
        btnNotif= findViewById(R.id.button2);

        FirebaseDatabase dbSuira = FirebaseDatabase.getInstance();
        FirebaseStorage dbSuiraStorage = FirebaseStorage.getInstance();
        dbUsers = dbSuira.getReference("userClient");
        dbTags =  dbSuira.getReference("tag");
        registerAuth = FirebaseAuth.getInstance();
        uploadImages = findViewById(R.id.profilee);
        storageUserClients = dbSuiraStorage.getReference("images/userClient");

        tagsFire = new ArrayList<String>();

        uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageFireBase();
            }
        });

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


        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserClientClass user = (UserClientClass) getIntent().getSerializableExtra("userObject");
                List<String> tagNames = getSelectedTags();
                Map<String ,TagClass> tagsUser = generateTags(tagNames);

                System.out.println(user.getName());
                user.setTag(tagsUser);



                FirebaseUser currentUser = registerAuth.getCurrentUser();
                final String userId = currentUser.getUid();
                dbUsers.child(userId).setValue(user);

                storageUserClients.child(userId).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageUserClients.child(userId).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                final String downloadUrl =
                                        uri.toString();
                                Log.i("URL+1", downloadUrl);
                                dbUsers.child(userId).child("photoDownloadURL").setValue(downloadUrl);

                            }
                        });
                    }
                });

                Bundle bund = new Bundle();

                String msn = new String("Thanks for Using Suira\n \n \n Suira is Working To Find What You Need");
                String btnMsn = new String("Log In");
                String activityName = "LogIn";

                Intent intent= new Intent(getApplicationContext(), PopUp.class);

                bund.putString("mensaje", msn);
                bund.putString("contenidoBoton", btnMsn);
                bund.putString("sender", activityName );
                intent.putExtras(bund);

                startActivity(intent);
            }
        });

    }

    private void uploadImageFireBase() {
        PermissionsActions.askPermission(this, PermissionIds.REQUEST_READ_EXTERNAL_STORAGE);
        selectImage();
    }

    private void selectImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent pickImage = new Intent(Intent.ACTION_PICK);
            pickImage.setType("image/*");
            startActivityForResult(pickImage, PermissionIds.IMAGE_PICKER_REQUEST);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PermissionIds.IMAGE_PICKER_REQUEST:
                if(resultCode == RESULT_OK){
                    try {
                        imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        uploadImages.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PermissionIds.REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {

                }
                break;
        }
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
                            if (txt_showselected.getText().toString().equals(".")) {
                                txt_showselected.setText(tags.getText().toString());
                                canttags++;
                            } else {
                                txt_showselected.setText(txt_showselected.getText().toString() + "  " + tags.getText().toString());
                                canttags++;
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Maximo 5 Tags. ",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //Si el boton ya a sido clickeado cambia el estilo y borra lo necesario de los tags del usuario
                        if (first == false) {
                            String withTag = txt_showselected.getText().toString();

                            String withoutTag = withTag.replace(tags.getText().toString(), "");
                            txt_showselected.setText(withoutTag);
                            first = true;
                            canttags--;

                        } else {
                            String withTag = txt_showselected.getText().toString();

                            String withoutTag = withTag.replace("  " + tags.getText().toString(), "");
                            txt_showselected.setText(withoutTag);
                            canttags--;
                        }
                        tags.setBackgroundResource(R.drawable.btn_tag);

                        click = false;

                    }

                }
            });

            // Se añade el boton al gridLayout
            gridLayout.addView(tags, childCount);
        }
    }

    private List<String> getSelectedTags()
    {
        List<String> items = new ArrayList<String>();
        StringTokenizer st1 = new StringTokenizer(txt_showselected.getText().toString());

        for (int i = 1; st1.hasMoreTokens(); i++)
            items.add(st1.nextToken());
        return items;
    }

    //convierte la lista de tags en un hashmap para guardarlos en firebase
    private Map<String ,TagClass> generateTags(List<String> tagNames){
        Map<String ,TagClass> res = new HashMap<String , TagClass>();

        for(int i =0 ; i<tagNames.size() ; ++i){
            System.out.println("------------------------------------------");
            System.out.println(i);
            res.put(tagNames.get(i) , new TagClass(5.0 , 1));

        }

        return res;
    }
}
