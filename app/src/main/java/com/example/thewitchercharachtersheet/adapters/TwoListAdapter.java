package com.example.thewitchercharachtersheet.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.objects.Feature;
import com.example.thewitchercharachtersheet.objects.Item;

import java.util.List;

public class TwoListAdapter extends BaseAdapter {

    private final Activity activity;
    private final List objects;
    private final List objectsNames;
    private LayoutInflater inflater;
    public TwoListAdapter(Activity activity, List objectsNames, List objects) {
        this.activity = activity;
        this.objectsNames = objectsNames;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
        {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.two_element_list, null);
        }
        TextView textName = convertView.findViewById(R.id.name);
        TextView textValue = convertView.findViewById(R.id.value);
        textName.setText((String) objectsNames.get(position));
        textValue.setText((String) objects.get(position));
        return convertView;
    }
}
