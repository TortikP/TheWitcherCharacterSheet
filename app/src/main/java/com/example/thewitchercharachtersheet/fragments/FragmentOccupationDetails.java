package com.example.thewitchercharachtersheet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;


import com.example.thewitchercharachtersheet.objects.WitcherCharacter;



public class FragmentOccupationDetails extends Fragment {

    View view;
    WitcherCharacter playerCharacter;
    public FragmentOccupationDetails() {
        // Required empty public constructor
    }




  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_occupation_details, container, false);
        playerCharacter = (WitcherCharacter) getArguments().getSerializable("character");
        TextView occupationTitle = view.findViewById(R.id.occupation_title);
        ListView detailList = view.findViewById(R.id.occupation_abilities);
        ListView equipmentList = view.findViewById(R.id.occupation_equipment);
        ImageView detailImage = view.findViewById(R.id.imageView1);
        ImageView detailClose = view.findViewById(R.id.close_occupation_details);
        TextView detailAbilityName = view.findViewById(R.id.occupation_main_ability);
        TextView detailAbilityDescription = view.findViewById(R.id.occupation_main_ability_description);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, playerCharacter.getOccupation().getOccupationAbilities());
        detailList.setAdapter(adapter);
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), playerCharacter.getOccupation().getStartingEquipment());
        equipmentList.setAdapter(itemAdapter);
        detailImage.setImageResource(playerCharacter.getOccupation().getImageId());
        detailAbilityName.setText(playerCharacter.getOccupation().getMainAbility().getFeatureName());
        detailAbilityDescription.setText(playerCharacter.getOccupation().getMainAbility().getDescription());
        occupationTitle.setText(playerCharacter.getOccupation().getName());
        detailClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack(R.id.fragmentOccupation, false, true);
            }
        });
        return view;
    }*/
}