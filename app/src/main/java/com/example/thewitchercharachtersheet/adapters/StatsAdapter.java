package com.example.thewitchercharachtersheet.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.adapters.objects.StatsElement;
import com.example.thewitchercharachtersheet.objects.Feature;

import java.util.List;
import java.util.Objects;

public class StatsAdapter extends BaseAdapter {
    private final Activity activity;
    private final List objects;
    private LayoutInflater inflater;
    private Feature feature;
    private boolean isShort;
    public StatsAdapter(Activity activity, List objects, boolean isShort) {
        this.activity = activity;
        this.objects = objects;
        this.isShort = isShort;
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(inflater == null)
        {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.stats_list_element, null);
        }
        TextView textName= convertView.findViewById(R.id.stat_name_text);
        TextView textValue= convertView.findViewById(R.id.stat_value_text);
        feature = (Feature) objects.get(position);
        if(!isShort) {
            textName.setText(feature.getFeatureName());
        }
        else {
            textName.setText(feature.getShortFeatureName());
        }
        textValue.setText(String.valueOf(feature.getFeatureValue()));
        if(!feature.isEnabled())
        {
            convertView.setEnabled(false);
            textName.setTextColor(activity.getColor(R.color.gray));
            textValue.setTextColor(activity.getColor(R.color.gray));
        }
        else if (!convertView.isEnabled()){
            convertView.setEnabled(true);
            textName.setTextColor(activity.getColor(R.color.black));
            textValue.setTextColor(activity.getColor(R.color.black));
        }
        return convertView;
    }

}
