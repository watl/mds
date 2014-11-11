package com.example.medisut.mds;


import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
/**
 * Created by MediSut on 8/13/2014.
 */
public class ActivityList extends Activity {
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<DatPopulation> datlist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Parse.initialize(this, "0Cic61dLR2wBTsk6pU3HXZsG6ql2FddnB1EDBNZH", "FBjRCaXStKlAu3wCD9mTfTwcI1eUYVQp7DUh2jc4");

        new RemoteDataTask().execute();
    }


    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ActivityList.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Parse.com List Toners");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            datlist = new ArrayList<DatPopulation>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Object");
                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending
                query.orderByAscending("createdAt");
                ob = query.find();
                for (ParseObject obj : ob) {
                    DatPopulation map = new DatPopulation();
                    map.setObjid((String) obj.getObjectId());
                    map.setCant((String) obj.get("cant"));
                    map.setPorc((String) obj.get("porc"));
                    map.setStatus((String) obj.get("status"));
                    map.setModelo((String) obj.get("modelo"));
                    map.setStock((String) obj.get("stock"));
                    datlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listV);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(ActivityList.this,datlist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }


}