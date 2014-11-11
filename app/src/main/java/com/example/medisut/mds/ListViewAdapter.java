package com.example.medisut.mds;

/**
 * Created by MediSut on 8/14/2014.
 */

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<DatPopulation> datlist = null;
    private ArrayList<DatPopulation> arraylist;

    public ListViewAdapter(Context context,List<DatPopulation> datlist) {
        mContext = context;
        this.datlist = datlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<DatPopulation>();
        this.arraylist.addAll(datlist);
    }

    public class ViewHolder {
        TextView marc;
        TextView stat;
        TextView mode;
        TextView sto;
        TextView cant;
        TextView porc;
        TextView id;
    }


    public int getCount() {
        return datlist.size();
    }


    public DatPopulation getItem(int position) {
        return datlist.get(position);
    }


    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.id = (TextView) view.findViewById(R.id.id1);
            holder.porc = (TextView) view.findViewById(R.id.porc1);
            holder.cant = (TextView) view.findViewById(R.id.cant1);
            holder.stat = (TextView) view.findViewById(R.id.marc1);
            holder.mode = (TextView) view.findViewById(R.id.mod1);
            holder.sto = (TextView) view.findViewById(R.id.stk1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.id.setText(datlist.get(position).getObjid());
        holder.porc.setText(datlist.get(position).getPorc());
        holder.cant.setText(datlist.get(position).getCant());
        holder.stat.setText(datlist.get(position).getStatus());
        holder.mode.setText(datlist.get(position).getModelo());
        holder.sto.setText(datlist.get(position).getStock());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, ActivityDet.class);
                // Pass all data rank
                intent.putExtra("objectId",(datlist.get(position).getObjid()));
                intent.putExtra("status",(datlist.get(position).getStatus()));

                intent.putExtra("porc",(datlist.get(position).getPorc()));
                intent.putExtra("cant",(datlist.get(position).getCant()));
                // Pass all data country
                intent.putExtra("modelo",
                        (datlist.get(position).getModelo()));
                // Pass all data population
                intent.putExtra("stock",
                        (datlist.get(position).getStock()));
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }



}
