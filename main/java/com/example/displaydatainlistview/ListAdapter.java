package com.example.displaydatainlistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {
    Activity context;
    ArrayList<Integer>id;
    ArrayList<String>name;
    ArrayList<String>address;

   public ListAdapter(Activity context, ArrayList<Integer> id, ArrayList<String> name, ArrayList<String> address){
        super(context,R.layout.list_items,name);
        this.context=context;
        this.id=id;
        this.name=name;
        this.address=address;
    }

    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_items,null,true);
        TextView textId=(TextView) rowView.findViewById(R.id.textId);
        TextView textName=(TextView)  rowView.findViewById(R.id.textName);
        TextView textAddress=(TextView) rowView.findViewById(R.id.textAddress);

        textId.setText(id.get(position).toString());
        textName.setText(name.get(position).toString());
        textAddress.setText(address.get(position).toString());

    return rowView;

    };
}
