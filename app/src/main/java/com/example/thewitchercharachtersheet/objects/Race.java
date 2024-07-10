package com.example.thewitchercharachtersheet.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Race implements Serializable {
    private String name;
    private String description;
    private ArrayList<String> abilities;
    public Race()
    {

    }
    public Race(String name)
    {
        this.name = name;
    }
    public Race(String name, String description, ArrayList<String> abilities)
    {
        this.name = name;
        this.description = description;
        this.abilities = abilities;
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

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }
}
