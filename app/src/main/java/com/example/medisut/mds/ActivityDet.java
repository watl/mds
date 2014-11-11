package com.example.medisut.mds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Toast;

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

import java.text.ParseException;


/**
 * Created by MediSut on 8/13/2014.
 */
public class ActivityDet extends Activity {
    private RadioButton r1,r2,r3;

    EditText edtStk;
    EditText edtCnt;
    EditText edtPorc;
    EditText edtskb;
    SeekBar seekbar;

    TextView txtmodelo;
    TextView txtmarca;
    TextView txtstock;
    TextView txtstatus;
    TextView txtporc;
    TextView txtcant;
    TextView txtid;

    String modelo;
    String marca;
    String stock;
    String status;
    String cant;
    String porcn;
    String id;
    Integer value=0,qty=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det);

        Parse.initialize(this, "0Cic61dLR2wBTsk6pU3HXZsG6ql2FddnB1EDBNZH", "FBjRCaXStKlAu3wCD9mTfTwcI1eUYVQp7DUh2jc4");

        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);
        r3=(RadioButton)findViewById(R.id.r3);

        Button btnUpd =(Button)findViewById(R.id.button);

          //edtseek = (EditText)findViewById(R.id.edtSeek);
          seekbar = (SeekBar)findViewById(R.id.seekBar);

        Intent i = getIntent();

        // Get the name
        modelo = i.getStringExtra("modelo");
        id=i.getStringExtra("objectId");
        status=i.getStringExtra("status");
        stock=i.getStringExtra("stock");
        cant=i.getStringExtra("cant");
        porcn=i.getStringExtra("porc");

        //EditText
        edtStk = (EditText)findViewById(R.id.edtStock);
        edtCnt = (EditText)findViewById(R.id.edtCant);
        edtPorc = (EditText)findViewById(R.id.edtPorc);


        txtmodelo = (TextView) findViewById(R.id.modelo);
        txtid = (TextView) findViewById(R.id.txtID);
        //  txtstock = (TextView) findViewById(R.id.stock);
       // txtstatus = (TextView) findViewById(R.id.marca);

        //   txtcant = (TextView) findViewById(R.id.cant);
        //  txtporc = (TextView) findViewById(R.id.porc);

        // Load the results into the TextViews
        txtmodelo.setText(modelo);
        txtid.setText(id);
      //  txtstatus.setText(status);
        //   txtstock.setText(stock);
        //  txtcant.setText(cant);
        //  txtporc.setText(porcn);

        //EditText
        edtStk.setText(stock);
        edtCnt.setText(cant);
        edtPorc.setText(porcn);


        if (status.equals("Bueno")){
        r1.setChecked(true);
        } else if (status.equals("Dañado")){
            r2.setChecked(true);
        } else if (status.equals("Usado")){
            r3.setChecked(true);
        }

       value =  Integer.parseInt(porcn.toString());

        seekbar.setProgress(value);

        seekbar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener()
        {


            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser)
            {

                edtPorc.setText(""+progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }
        });


     btnUpd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             update(v);
         }
     });



    }


    public void update(View v){
        final String var1=edtStk.getText().toString();
        final String var2=edtPorc.getText().toString();
        final String var3=txtid.getText().toString();

        //qty = Integer.parseInt(stock.toString());
          qty=Integer.parseInt(var1.toString());


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Object");

        query.getInBackground(var3, new GetCallback<ParseObject>() {
                  @Override
            public void done(ParseObject obj, com.parse.ParseException e) {
                if (e == null) {
                    obj.put("stock", var1);
                    obj.put("porc", var2);
                    obj.saveInBackground();

                }
            }
        });


        if (qty < 3){
            Toast.makeText(this,"Solicitar Toner de este Modelo",Toast.LENGTH_LONG).show();
        }


        /*
        if (r1.isChecked()==true){
            Toast.makeText(this,"Bueno",Toast.LENGTH_LONG).show();
        } else if (r2.isChecked()==true) {
            Toast.makeText(this,"Usado",Toast.LENGTH_LONG).show();
        }  else if(r3.isChecked()==true) {
            Toast.makeText(this,"Dañado",Toast.LENGTH_LONG).show();
        }
        */



    }



}
