package com.example.thewitchercharachtersheet.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.objects.Item;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<String> values;
    private final Activity activity;
    public RecyclerAdapter(Activity activity,List<String> values) {
        this.activity = activity;
        this.values = values;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.list_element, parent, false);
        RecyclerAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(values.get(position));
        if(position%2==0)
        {
            System.out.println("Change back");
            holder.textView.setBackgroundColor(activity.getColor(R.color.background_darker));
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
        }
    }
}
