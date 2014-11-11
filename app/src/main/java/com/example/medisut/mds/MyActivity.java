package com.example.medisut.mds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.PushService;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Parse.initialize(this, "0Cic61dLR2wBTsk6pU3HXZsG6ql2FddnB1EDBNZH", "FBjRCaXStKlAu3wCD9mTfTwcI1eUYVQp7DUh2jc4");

       // PushService.setDefaultPushCallback(this, MyActivity.class); //quede aqui   https://www.parse.com/tutorials/android-push-notifications
       ParseAnalytics.trackAppOpened(getIntent());
       PushService.setDefaultPushCallback(this, MyActivity.class);

        PushService.setDefaultPushCallback(this, MyActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();

       //canal para enviar notificacion
        Context ctx = this.getApplicationContext();
        PushService.subscribe(ctx, "parse", MyActivity.class);

        //botones
        Button btnNew =(Button)findViewById(R.id.btnN);
        Button btnList=(Button)findViewById(R.id.btnL);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, ActivityNew.class);
                startActivity(intent);
            }
        });


        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, ActivityList.class);
                startActivity(intent);
            }
        });

    }

  /*  public void Send(View v){
        ParsePush push = new ParsePush();
        push.setChannel("parse");
        push.setMessage("Mensaje PUSH desde el Dispositivo");
        push.sendInBackground();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
     /*   if (id == R.id.action_settings) {
            return true;
        }  */
        return super.onOptionsItemSelected(item);
    }
}
