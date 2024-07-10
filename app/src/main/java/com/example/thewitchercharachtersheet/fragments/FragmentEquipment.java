package com.example.thewitchercharachtersheet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.adapters.EquipmentAdapter;
import com.example.thewitchercharachtersheet.adapters.TwoListAdapter;
import com.example.thewitchercharachtersheet.objects.Armor;
import com.example.thewitchercharachtersheet.objects.Item;
import com.example.thewitchercharachtersheet.objects.Weapon;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEquipment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEquipment extends Fragment implements EquipmentAdapter.OnNoteListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView weaponList;
    RecyclerView armorList;
    RecyclerView itemList;
    WitcherCharacter playerCharacter;
    EditText money;
    View view;
    List<Weapon> weapons = new ArrayList<>();
    List<Armor> armor = new ArrayList<>();
    List<Item> equipment = new ArrayList<>();
    public FragmentEquipment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerCharacter = (WitcherCharacter) getArguments().getSerializable("character");
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEquipment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEquipment newInstance(String param1, String param2) {
        FragmentEquipment fragment = new FragmentEquipment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_equipment, container, false);
        weaponList = view.findViewById(R.id.weapons);
        armorList = view.findViewById(R.id.armor);
        itemList = view.findViewById(R.id.items);
        money = view.findViewById(R.id.money_field);
        playerCharacter = (WitcherCharacter) getArguments().getSerializable("character");
        money.setText(String.valueOf(playerCharacter.getOccupation().getStartingMoney()));
        System.out.println(playerCharacter.getStartingEquipment().size());
        Weapon weaponTitle = new Weapon();
        Armor armorTitle = new Armor();
        Item itemTitle = new Item();
        weaponTitle.setItemType(getString(R.string.type_weapon));
        armorTitle.setItemType(getString(R.string.type_armor));
        itemTitle.setItemType(getString(R.string.type_equipment));
        weapons.add(weaponTitle);
        armor.add(armorTitle);
        equipment.add(itemTitle);
        for(int i = 0; i < playerCharacter.getStartingEquipment().size();i++) {
            if(playerCharacter.getStartingEquipment().get(i).getItemType() == getString(R.string.type_weapon)) {
                weapons.add((Weapon) playerCharacter.getStartingEquipment().get(i));
                System.out.println(weapons.get(0).getName());
            }
            else if(playerCharacter.getStartingEquipment().get(i).getItemType() == getString(R.string.type_armor)) {
                armor.add((Armor) playerCharacter.getStartingEquipment().get(i));
                System.out.println(armor.get(0).getName());
            }
            else {
                equipment.add(playerCharacter.getStartingEquipment().get(i));
                System.out.println(equipment.get(0).getName());
            }
        }
        weaponList.setLayoutManager(new LinearLayoutManager(getActivity()));
        armorList.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        EquipmentAdapter.OnNoteListener weaponListener = new EquipmentAdapter.OnNoteListener() {
            @Override
            public void onNoteClick(int position) {
                if(position != 0) {
                    getItemInfo(weapons.get(position));
                }
            }
        };
        EquipmentAdapter.OnNoteListener armorListener = new EquipmentAdapter.OnNoteListener() {
            @Override
            public void onNoteClick(int position) {
                if(position != 0) {
                    getItemInfo(armor.get(position));
                }
            }
        };
        EquipmentAdapter.OnNoteListener equipmentListener = new EquipmentAdapter.OnNoteListener() {
            @Override
            public void onNoteClick(int position) {
                if(position != 0) {
                    getItemInfo(equipment.get(position));
                }
            }
        };
        EquipmentAdapter adapter = new EquipmentAdapter(getActivity(), weapons, weaponListener);
        weaponList.setAdapter(adapter);
        adapter = new EquipmentAdapter(getActivity(), armor, armorListener);
        armorList.setAdapter(adapter);
        adapter = new EquipmentAdapter(getActivity(), equipment, equipmentListener);
        itemList.setAdapter(adapter);

        return view;
    }

    void getItemInfo(Item item)
    {
        View popUpView = getLayoutInflater().inflate(R.layout.equipment_details, null);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        view.post(new Runnable(){
            @Override
            public void run(){
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });
        TwoListAdapter twoListAdapter;
        TextView infoName = popUpView.findViewById(R.id.item_header);
        infoName.setText(item.getName());
        ListView infoList = popUpView.findViewById(R.id.item_characteristics);
        ImageView close = popUpView.findViewById(R.id.close_info);
        List<String> itemNames = Arrays.asList(getResources().getStringArray(R.array.item_name_fields));
        List<String> itemValue = new ArrayList<>(Arrays.asList(item.getItemType(), item.getRarity(),
                String.valueOf(item.getWeight()),
                String.valueOf(item.getCost())));
        if(item.getItemType() == getString(R.string.type_weapon))
        {
            Weapon weapon = (Weapon) item;
            itemNames = Stream.concat(itemNames.stream(), Arrays.stream(getResources().getStringArray(R.array.weapon_name_fields))).collect(Collectors.toList());
            itemValue = Stream.concat(itemValue.stream(), Stream.of(weapon.getWeaponType(), weapon.getDamage(), String.valueOf(weapon.getAccuracy()),
                    String.valueOf(weapon.getReliability()), String.valueOf(weapon.getRange()), weapon.getObscurity())).collect(Collectors.toList());
        }
        else if(item.getItemType() == getString(R.string.type_armor))
        {
            Armor armor = (Armor) item;
            itemNames = Stream.concat(itemNames.stream(), Arrays.stream(getResources().getStringArray(R.array.armor_name_fields))).collect(Collectors.toList());
            itemValue = Stream.concat(itemValue.stream(), Stream.of(armor.getArmorType(), armor.getArmorCover(), String.valueOf(armor.getArmorStrength()),
                    String.valueOf(armor.getArmorStiffness()))).collect(Collectors.toList());
        }
        twoListAdapter = new TwoListAdapter(getActivity(), itemNames, itemValue);
        infoList.setAdapter(twoListAdapter);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
    @Override
    public void onNoteClick(int position) {

    }
}