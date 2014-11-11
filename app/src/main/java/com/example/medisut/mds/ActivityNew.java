package com.example.medisut.mds;

import android.app.Activity;
import android.os.Bundle;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;
import com.parse.ParseClassName;

import static android.widget.Toast.makeText;


/**
 * Created by MediSut on 8/8/2014.
 */
public class ActivityNew extends MyActivity {


    private EditText txt1,txt2,txt3,txt4;
    private TextView txtvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Parse.initialize(this, "0Cic61dLR2wBTsk6pU3HXZsG6ql2FddnB1EDBNZH", "FBjRCaXStKlAu3wCD9mTfTwcI1eUYVQp7DUh2jc4");

        txt1=(EditText)findViewById(R.id.txtmod);
        txt2=(EditText)findViewById(R.id.txtmar);
        txt3=(EditText)findViewById(R.id.txtdes);
        txt4=(EditText)findViewById(R.id.txtstc);

//obtener datos desde parse.com
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Object");
        query.whereEqualTo("modelo", "505A");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                ArrayList<String> curs = null;
                if (e == null) {
                    curs = new ArrayList<String>();

                    for (ParseObject obj : objects)
                    {
                        String mod = obj.getString("marca");
                        txtvar= (TextView) findViewById(R.id.txtres);
                        txtvar.setText(mod);
                    }

                  /*  String mod = objects.  getString("modelo");
                    txtvar= (TextView) findViewById(R.id.txtres);
                    txtvar.setText(mod);*/
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });



        Button btnsave =(Button)findViewById(R.id.button);

        btnsave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                save(v);
            }
        });


    }

    public void save(View v){
        String var1=txt1.getText().toString();
        String var2=txt2.getText().toString();
        String var3=txt3.getText().toString();
        String var4=txt4.getText().toString();

        if (txt1.getText().toString().length()==0 || txt1.getText()==null){
            txt1.setError("Campo Modelo es requerido!");
            return;
          } else {
         txt1.getText().toString();
        }

        if (txt2.getText().toString().length()==0 || txt2.getText()==null ){
            txt2.setError("Campo Marca es requerido!");
            return;
        } else {
          txt2.getText().toString();
        }

        if (txt3.getText().toString().length()==0 || txt3.getText()==null){
            txt3.setError("Campo Destino es requerido!");
            return;
        } else {
           txt3.getText().toString();
        }

        if (txt4.getText().toString().length()==0 || txt4.getText() == null){
            txt4.setError("Campo Stock es requerido!");
            return;
         } else {
            txt4.getText().toString();
        }

        ParseObject testObject = new ParseObject("Object");
        testObject.put("modelo",var1);
        testObject.put("marca",var2);
        testObject.put("destino",var3);
        testObject.put("stock",var4);
        testObject.put("status","Bueno");
        testObject.put("porc","100");
        testObject.put("cant","");
        testObject.saveInBackground();

        Toast.makeText(this,"Registro Guardado en Parse.com",Toast.LENGTH_LONG).show();

        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");

    }



}
