package com.example.thewitchercharachtersheet.fragments;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.adapters.ItemAdapter;
import com.example.thewitchercharachtersheet.adapters.RecyclerAdapter;
import com.example.thewitchercharachtersheet.interfaces.OnItemClicked;
import com.example.thewitchercharachtersheet.objects.Ability;
import com.example.thewitchercharachtersheet.objects.Armor;
import com.example.thewitchercharachtersheet.objects.Item;
import com.example.thewitchercharachtersheet.objects.Occupation;
import com.example.thewitchercharachtersheet.objects.Weapon;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentOccupation extends Fragment implements ItemAdapter.OnNoteListener{

    View view;
    Occupation[] occupations;
    ArrayList<String> mainAbilities;
    ArrayList<Item> occupationItems;
    ImageView currentImage;
    TextView currentDescription;
    ImageView prevImage;
    ImageView nextImage;
    int currentOccupation = 0;
    ConstraintLayout layout;
    Button nextImageButton;
    Button prevImageButton;
    WitcherCharacter playerCharacter;
    List<Item> equipment;
    TextView equipmentLeft;
    ItemAdapter itemAdapter;
    int itemsLeft = 5;
    List<Item> allEquipment;
    public FragmentOccupation() {
        // Required empty public constructor

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerCharacter = (WitcherCharacter) getArguments().getSerializable("character");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TypedArray occupationsResource = getResources().obtainTypedArray(R.array.occupation_array);
        TypedArray itemDef;
        TypedArray occupationDetailsArray;
        TypedArray occupationItem;
        TypedArray occupationItemAndAmount;
        occupations = new Occupation[occupationsResource.length()];
        for (int i = 0; i < occupationsResource.length(); i++) {
            mainAbilities = new ArrayList<>();
            occupationItems = new ArrayList<>();
            int resId = occupationsResource.getResourceId(i, -1);
            if (resId < 0) {
                continue;
            }
            itemDef = getResources().obtainTypedArray(resId);
            occupationDetailsArray = getResources().obtainTypedArray(itemDef.getResourceId(6, -1));
            for(int j = 0;j < occupationDetailsArray.length();j++)
            {
                mainAbilities.add(occupationDetailsArray.getString(j));
            }
            occupationDetailsArray = getResources().obtainTypedArray(itemDef.getResourceId(8, -1));
            for (int g = 0; g < occupationDetailsArray.length();g++)
            {
                occupationItem = getResources().obtainTypedArray(occupationDetailsArray.getResourceId(g, -1));
                occupationItemAndAmount = getResources().obtainTypedArray(occupationItem.getResourceId(0, -1));
                if(Objects.equals(occupationItemAndAmount.getString(0), getString(R.string.type_weapon))) {
                    occupationItems.add(new Weapon(occupationItemAndAmount.getString(0), occupationItemAndAmount.getString(1),
                            occupationItemAndAmount.getString(2), occupationItemAndAmount.getFloat(3, -1),
                            occupationItemAndAmount.getInt(4, -1), occupationItem.getInt(1, -1),occupationItemAndAmount.getString(5),
                            occupationItemAndAmount.getString(6), occupationItemAndAmount.getInt(7, -1), occupationItemAndAmount.getInt(8, -1),
                            occupationItemAndAmount.getInt(9, -1), occupationItemAndAmount.getString(10), occupationItemAndAmount.getString(11)));
                }
                else if(Objects.equals(occupationItemAndAmount.getString(0), getString(R.string.type_armor)))
                {
                    //Для брони
                    occupationItems.add(new Armor(occupationItemAndAmount.getString(0), occupationItemAndAmount.getString(1),
                            occupationItemAndAmount.getString(2), occupationItemAndAmount.getFloat(3, -1),
                            occupationItemAndAmount.getInt(4, -1), occupationItem.getInt(1,-1),
                            occupationItemAndAmount.getString(5), occupationItemAndAmount.getString(6), occupationItemAndAmount.getInt(7,-1),
                            occupationItemAndAmount.getInt(8,-1), occupationItemAndAmount.getString(9)));
                }
                else {
                    occupationItems.add(new Item(occupationItemAndAmount.getString(0), occupationItemAndAmount.getString(1),
                            occupationItemAndAmount.getString(2), occupationItemAndAmount.getFloat(3, -1),
                            occupationItemAndAmount.getInt(4, -1), occupationItem.getInt(1, -1)));
                }
            }
            occupations[i] = new Occupation(i, itemDef.getString(0), itemDef.getString(1),
                    new Ability(itemDef.getString(3), itemDef.getString(4)),
                    itemDef.getResourceId(2,-1), mainAbilities, itemDef.getInt(7, 0), occupationItems, itemDef.getString(9));
            occupations[i].getMainAbility().setDescription(itemDef.getString(5));
        }
        view = inflater.inflate(R.layout.fragment_occupation, container, false);
        layout = view.findViewById(R.id.occupation_screen);
        currentImage = view.findViewById(R.id.currentProfImage);
        currentDescription = view.findViewById(R.id.profDescription);
        prevImage = view.findViewById(R.id.prevProfImage);
        nextImage =  view.findViewById(R.id.nextProfImage);
        prevImage.setImageResource(occupations[occupations.length-1].getImageId());
        currentImage.setImageResource(occupations[currentOccupation].getImageId());
        nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
        currentDescription.setText(occupations[currentOccupation].getDescription());
        nextImageButton = view.findViewById(R.id.nextButton);
        prevImageButton = view.findViewById(R.id.prevButton);

        nextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCurrentProfession(v);
            }
        });
        prevImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCurrentProfession(v);
            }
        });
        view.findViewById(R.id.to_characters).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.fragmentMain);
            }
        });
        view.findViewById(R.id.to_life_path).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerCharacter.getOccupation()!=null && playerCharacter.getOccupation() == occupations[currentOccupation]) {
                    if(playerCharacter.getStartingEquipment().size() < 5)
                    {
                        Toast toast = Toast.makeText(getActivity(), "Вам нужно выбрать 5 стартовых предметов", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("character", playerCharacter);
                        Navigation.findNavController(v).navigate(R.id.fragmentBackstory, bundle);
                    }
                }
                else {
                    Toast toast = Toast.makeText(getActivity(), "Выбертие стартовое снаряжение", Toast.LENGTH_SHORT);
                    toast.show();
                    ShowOccupationDetails();
                }
            }
        });

        currentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowOccupationDetails();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void ShowOccupationDetails()
    {
        playerCharacter.setOccupation(occupations[currentOccupation]);
        View popUpView = getLayoutInflater().inflate(R.layout.occupation_details, null);
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
        TextView occupationTitle = popUpView.findViewById(R.id.occupation_title);
        RecyclerView detailList = popUpView.findViewById(R.id.occupation_abilities);
        RecyclerView equipmentList = popUpView.findViewById(R.id.occupation_equipment);
        RecyclerView knownSpells = popUpView.findViewById(R.id.magical_abilities);
        ImageView detailImage = popUpView.findViewById(R.id.imageView1);
        ImageView detailClose = popUpView.findViewById(R.id.close_occupation_details);
        TextView detailAbilityName = popUpView.findViewById(R.id.occupation_main_ability);
        TextView detailAbilityDescription = popUpView.findViewById(R.id.occupation_main_ability_description);
        allEquipment = playerCharacter.getOccupation().getStartingEquipment();
        equipmentLeft = popUpView.findViewById(R.id.equipment_left);
        itemsLeft = 5;
        TextView energyValue = popUpView.findViewById(R.id.energy_value);
        TextView mainAbility = popUpView.findViewById(R.id.key_ability);
        equipment = new ArrayList<>();
        energyValue.setText(String.valueOf(playerCharacter.getOccupation().getEnergy()));
        mainAbility.setText(playerCharacter.getOccupation().getMainAbility().getFeatureName());
        equipmentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        detailList.setLayoutManager(new LinearLayoutManager(getActivity()));
        knownSpells.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemAdapter = new ItemAdapter(getActivity(), playerCharacter.getOccupation().getStartingEquipment(), this);
        equipmentList.setAdapter(itemAdapter);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), playerCharacter.getOccupation().getOccupationAbilities());
        detailList.setAdapter(recyclerAdapter);
        List<String> spellList = new ArrayList<>();
        spellList.add(playerCharacter.getOccupation().getSpells());
        recyclerAdapter = new RecyclerAdapter(getActivity(),spellList);
        knownSpells.setAdapter(recyclerAdapter);
        detailImage.setImageResource(playerCharacter.getOccupation().getImageId());
        String keyAbility = playerCharacter.getOccupation().getMainAbility().getFeatureName() + "\n(" +
                playerCharacter.getOccupation().getMainAbility().getKeyStatName() + ")";
        detailAbilityName.setText(keyAbility);
        detailAbilityDescription.setText(playerCharacter.getOccupation().getMainAbility().getDescription());
        occupationTitle.setText(playerCharacter.getOccupation().getName());
        detailClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                playerCharacter.setStartingEquipment(equipment);
                System.out.println(playerCharacter.getStartingEquipment());
            }
        });
    }

    private void SetCurrentProfession(View view) {
        if (view.getId() == R.id.nextButton) {
            if (currentOccupation >= occupations.length - 1) {
                currentOccupation = 0;
                prevImage.setImageResource(occupations[occupations.length-1].getImageId());
                nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
            } else {
                currentOccupation++;
                if (currentOccupation >= occupations.length - 1) {
                    nextImage.setImageResource(occupations[0].getImageId());
                } else {
                    nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
                }
                prevImage.setImageResource(occupations[currentOccupation-1].getImageId());
            }
        }
        else if(view.getId() == R.id.prevButton)
        {
            if(currentOccupation == 0)
            {
                currentOccupation = occupations.length-1;
                prevImage.setImageResource(occupations[currentOccupation-1].getImageId());
                nextImage.setImageResource(occupations[0].getImageId());
            }
            else {
                currentOccupation--;
                if (currentOccupation == 0)
                {
                    prevImage.setImageResource(occupations[occupations.length-1].getImageId());
                }
                else
                {
                    prevImage.setImageResource(occupations[currentOccupation-1].getImageId());
                }
                nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
            }
        }
        currentImage.setImageResource(occupations[currentOccupation].getImageId());
        currentDescription.setText(occupations[currentOccupation].getDescription());
    }

    @Override
    public void onNoteClick(int position) {
        Item item = allEquipment.get(position);
        if(!item.isTaken() && equipment.size() < 5) {
            item.setTaken(true);
            equipment.add(item);
            itemsLeft--;
            String newText = String.format("Снаряжение \n(выберите %d)", itemsLeft);
            equipmentLeft.setText(newText);
        }
        else if(item.isTaken()){
            item.setTaken(false);
            equipment.remove(item);
            itemsLeft++;
            String newText = String.format("Снаряжение \n(выберите %d)", itemsLeft);
            equipmentLeft.setText(newText);
        }
        itemAdapter.notifyItemChanged(position, item);
    }

}