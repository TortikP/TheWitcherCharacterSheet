package com.example.thewitchercharachtersheet.adapters;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.objects.Armor;
import com.example.thewitchercharachtersheet.objects.Item;
import com.example.thewitchercharachtersheet.objects.Weapon;

import java.util.List;
import java.util.Objects;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder>{

    private final Activity activity;
    private final List objects;
    private Item item;
    private EquipmentAdapter.OnNoteListener onNoteListener;
    public EquipmentAdapter(Activity activity, List objects, EquipmentAdapter.OnNoteListener onNoteListener) {
        this.activity = activity;
        this.objects = objects;
        this.onNoteListener = onNoteListener;
    }


    @NonNull
    @Override
    public EquipmentAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //inflate the indiv item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipment_list_element, parent,false);
        return new EquipmentAdapter.ViewHolder(view, this.onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentAdapter.ViewHolder holder, int position) {
        item = (Item) objects.get(position);
        if(position == 0){
            holder.dopinfo.setImageDrawable(null);
            holder.equipmentView.setBackgroundColor(activity.getColor(R.color.toolbar));
            holder.parameter1.setTextColor(activity.getColor(R.color.white));
            holder.parameter2.setTextColor(activity.getColor(R.color.white));
            holder.name.setTextColor(activity.getColor(R.color.white));
            if(Objects.equals(item.getItemType(), activity.getResources().getString(R.string.type_weapon)))
            {
                holder.name.setText("Оружие");
                holder.parameter1.setText("Вес");
                holder.parameter2.setText("Цена");
            }
            else if(Objects.equals(item.getItemType(), activity.getResources().getString(R.string.type_armor)))
            {
                holder.name.setText("Доспех");
                holder.parameter1.setText("Вес");
                holder.parameter2.setText("Цена");
            }
            else {
                holder.name.setText("Снаряжение");
                holder.parameter1.setText("Вес");
                holder.parameter2.setText("Цена");
            }
        }
        else {
            holder.name.setText(item.getName());
            holder.parameter1.setTextColor(activity.getColor(R.color.black));
            if(position%2==0)
            {
                holder.equipmentView.setBackgroundColor(activity.getColor(R.color.background_darker));
            }
            else {
                holder.equipmentView.setBackgroundColor(activity.getColor(R.color.background));
            }
            if(Objects.equals(item.getItemType(), activity.getResources().getString(R.string.type_weapon)))
            {
                Weapon weapon = (Weapon) objects.get(position);
                holder.parameter1.setText(String.valueOf(weapon.getWeight()));
                holder.parameter2.setText(String.valueOf(weapon.getCost()));
            }
            else if(Objects.equals(item.getItemType(), activity.getResources().getString(R.string.type_armor)))
            {
                Armor armor = (Armor) objects.get(position);
                holder.parameter1.setText(String.valueOf(armor.getWeight()));
                holder.parameter2.setText(String.valueOf(armor.getCost()));
            }
            else {
                holder.parameter1.setText(String.valueOf(item.getWeight()));
                holder.parameter2.setText(String.valueOf(item.getCost()));
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
        private final TextView name;
        private final TextView parameter1;
        private final TextView parameter2;
        private final ImageView dopinfo;
        private final LinearLayout equipmentView;
        EquipmentAdapter.OnNoteListener onNoteListener;

        public ViewHolder(View view, EquipmentAdapter.OnNoteListener onNoteListener){
            super(view);
            this.name = view.findViewById(R.id.equipment_name);
            this.parameter1 = view.findViewById(R.id.parameter1);
            this.parameter2 = view.findViewById(R.id.parameter2);
            this.dopinfo = view.findViewById(R.id.dop_info);
            this.equipmentView = view.findViewById(R.id.equipment_view);
            view.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            this.onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
