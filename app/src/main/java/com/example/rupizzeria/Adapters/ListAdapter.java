package com.example.rupizzeria.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rupizzeria.R;

import java.util.ArrayList;
/**
 * This is the Adapter for the lists used in the application views.
 *
 */
public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;

    public ListAdapter(Activity context, ArrayList<String> maintitle) {
        super(context, R.layout.mylist, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;

    }
    /**
     * To show the list
     */
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView titleText = rowView.findViewById(R.id.info);
        titleText.setText(maintitle.get(position));
        return rowView;

    }
}
