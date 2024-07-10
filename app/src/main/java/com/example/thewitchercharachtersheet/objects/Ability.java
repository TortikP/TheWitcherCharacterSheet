package com.example.thewitchercharachtersheet.objects;

import java.io.Serializable;

public class Ability extends Feature implements Serializable {

    private String keyStatName;
    private int costModifier = 1;
    private String description;

    public Ability(String name)
    {
        super(name, 0, "");

    }
    public Ability(String name, String keyStatName)
    {
        super(name, 0, "");
        this.keyStatName = keyStatName;
    }
    public int getCostModifier() {
        return costModifier;
    }

    public void setCostModifier(int costModifier) {
        this.costModifier = costModifier;
    }

    public String getKeyStatName() {
        return keyStatName;
    }

    public void setKeyStatName(String keyStatName) {
        this.keyStatName = keyStatName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
