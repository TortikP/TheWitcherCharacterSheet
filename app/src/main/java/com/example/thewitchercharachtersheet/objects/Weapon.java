package com.example.thewitchercharachtersheet.objects;

public class Weapon extends Item{
    String weaponType;
    String damage;
    int accuracy;
    int reliability;
    int range;
    String effects;
    String obscurity;
    public Weapon()
    {

    }
    public Weapon(String itemType, String name, String rarity, float weight, int cost,int amount) {
        super(itemType, name, rarity, weight, cost, amount);
    }
    public Weapon(String itemType, String name, String rarity, float weight, int cost, int amount, String weaponType, String damage, int accuracy, int reliability, int range, String effects, String obscurity) {
        super(itemType, name, rarity, weight, cost, amount);
        this.weaponType = weaponType;
        this.damage = damage;
        this.accuracy = accuracy;
        this.reliability = reliability;
        this.range = range;
        this.effects = effects;
        this.obscurity = obscurity;
    }

    public Weapon(String itemType, String name, String rarity, float weight, int cost) {
        super(name, rarity, weight, cost, itemType);
    }

    public Weapon(String itemType, String name, String rarity, float weight, int cost, String weaponType, String damage, int accuracy, int reliability, int range, String effects, String obscurity) {
        super(name, rarity, weight, cost, itemType);
        this.weaponType = weaponType;
        this.damage = damage;
        this.accuracy = accuracy;
        this.reliability = reliability;
        this.range = range;
        this.effects = effects;
        this.obscurity = obscurity;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public String getObscurity() {
        return obscurity;
    }

    public void setObscurity(String obscurity) {
        this.obscurity = obscurity;
    }
}
