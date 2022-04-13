package com.example.bivouac;

import android.Manifest;
import android.app.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.bivouac.R;
import com.example.bivouac.ui.MapsActivity;

public class MainActivity extends Activity {

    TextView mTextView;
    // mButton = findViewById(R.id.buttonContact);
    Button mButton = findViewById(R.id.buttonMap);
    EditText mtxtContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recupContact();


        mTextView = findViewById(R.id.Welcome_Text);
        mtxtContacts = findViewById(R.id.txtContacts);

        mButton.setEnabled(false);

       mtxtContacts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mButton.setEnabled(!editable.toString().isEmpty());
            }
        });




       /* mButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ContactActivityIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(ContactActivityIntent);
            }
        }); */
        findViewById(R.id.buttonMap) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MapsActivityIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(MapsActivityIntent);
                    }
                }
        );
    }

    public void recupContact() {
        //accès au contenu du mobile
        ContentResolver contentResolver = this.getContentResolver();

        // récupération des contacts dans un curseur
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                        ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
        if (cursor == null) {
            Log.d("recup", "******** error cursor ");
        } else {
            TextView txtContacts = findViewById(R.id.txtContacts);
            //parcours des contacts
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //affichage de la récupération
                txtContacts.setText(txtContacts.getText().toString() + "\n\r" + name + " : " + phone);
            }
            //femrer le curseur
            cursor.close();
        }
    }
}
