package com.example.thewitchercharachtersheet.objects;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class Occupation implements Serializable {

    private int id;
    private String name;
    private String description;
    private Ability mainAbility;
    private int imageId;
    private ArrayList<String> occupationAbilities;
    private int energy;
    private ArrayList<Item> startingEquipment;
    private String spells;
    private int spellCount = 0;
    private int startingMoney = 500;
    public Occupation(int id, String name, String description, Ability mainAbility, int imageId, ArrayList<String> occupationAbilities, int energy,
                      ArrayList<Item> startingEquipment, String spells)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mainAbility = mainAbility;
        this.imageId = imageId;
        this.occupationAbilities = occupationAbilities;
        this.energy = energy;
        this.startingEquipment = startingEquipment;
        this.spells = spells;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getOccupationAbilities() {
        return occupationAbilities;
    }

    public void setOccupationAbilities(ArrayList<String> occupationAbilities) {
        this.occupationAbilities = occupationAbilities;
    }

    public Ability getMainAbility() {
        return mainAbility;
    }

    public void setMainAbility(Ability mainAbility) {
        this.mainAbility = mainAbility;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public ArrayList<Item> getStartingEquipment() {
        return startingEquipment;
    }

    public void setStartingEquipment(ArrayList<Item> startingEquipment) {
        this.startingEquipment = startingEquipment;
    }
    public String getSpells() {
        return spells;
    }

    public void setSpells(String spells) {
        this.spells = spells;
    }

    public int getSpellCount() {
        return spellCount;
    }

    public void setSpellCount(int spellCount) {
        this.spellCount = spellCount;
    }

    public int getStartingMoney() {
        return startingMoney;
    }
    public void setStartingMoney(int startingMoney) {
        this.startingMoney = startingMoney;
    }
}
