package com.example.thewitchercharachtersheet.objects;

public class Armor extends Item{

    String armorType;
    String armorCover;
    int armorStrength;
    int armorStiffness;
    String effects;
    public Armor()
    {

    }
    public Armor(String itemType, String name, String rarity, float weight, int cost) {
        super(name, rarity, weight, cost, itemType);
    }

    public Armor(String itemType, String name, String rarity, float weight, int cost, int amount) {
        super(itemType, name, rarity, weight, cost, amount);
    }

    public Armor(String itemType, String name, String rarity, float weight, int cost, String armorType,
                 String armorCover, int armorStrength, int armorStiffness, String effects) {
        super(name, rarity, weight, cost, itemType);
        this.armorType = armorType;
        this.armorCover = armorCover;
        this.armorStrength = armorStrength;
        this.armorStiffness = armorStiffness;
        this.effects = effects;
    }

    public Armor(String itemType, String name, String rarity, float weight, int cost, int amount, String armorType, String armorCover, int armorStrength, int armorStiffness, String effects) {
        super(itemType, name, rarity, weight, cost, amount);
        this.armorType = armorType;
        this.armorCover = armorCover;
        this.armorStrength = armorStrength;
        this.armorStiffness = armorStiffness;
        this.effects = effects;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public String getArmorCover() {
        return armorCover;
    }

    public void setArmorCover(String armorCover) {
        this.armorCover = armorCover;
    }

    public int getArmorStrength() {
        return armorStrength;
    }

    public void setArmorStrength(int armorStrength) {
        this.armorStrength = armorStrength;
    }

    public int getArmorStiffness() {
        return armorStiffness;
    }

    public void setArmorStiffness(int armorStiffness) {
        this.armorStiffness = armorStiffness;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }
}
