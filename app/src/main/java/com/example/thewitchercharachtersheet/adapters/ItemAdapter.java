package com.example.thewitchercharachtersheet.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.interfaces.OnItemClicked;
import com.example.thewitchercharachtersheet.objects.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final Activity activity;
    private final List objects;
    private Item item;
    private OnNoteListener onNoteListener;
    public ItemAdapter(Activity activity, List objects, OnNoteListener onNoteListener) {
        this.activity = activity;
        this.objects = objects;
        this.onNoteListener = onNoteListener;
    }


    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //inflate the indiv item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent,false);

        return new ViewHolder(view, this.onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        item = (Item) objects.get(position);
        holder.textView.setText(item.getName());
        if(item.getAmount() > 1)
        {
            String amountText = holder.textView.getText().toString() + " x" + item.getAmount();
            holder.textView.setText(amountText);
        }
        if(item.isTaken())
        {
            holder.textView.setTextColor(activity.getColor(R.color.white));
            holder.textView.setBackgroundColor(activity.getColor(R.color.toolbar));
        }
        else {
            holder.textView.setTextColor(activity.getColor(R.color.black));
            if(position%2==0)
            {
                System.out.println("Change back");
                holder.textView.setBackgroundColor(activity.getColor(R.color.background_darker));
            }
            else {
                holder.textView.setBackgroundColor(activity.getColor(R.color.background));
            }
        }

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView textView;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener){
            super(view);
            this.textView = view.findViewById(R.id.text1);
            view.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            this.onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
