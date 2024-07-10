package com.example.thewitchercharachtersheet.objects;

public class Item {
    private String name;
    private String rarity;
    private float weight;
    private int cost;
    private String itemType;
    private int amount = 1;
    private boolean isTaken = false;

    public Item()
    {

    }

    public Item(String name, String rarity, float weight, int cost, String itemType) {
        this.name = name;
        this.rarity = rarity;
        this.weight = weight;
        this.cost = cost;
        this.itemType = itemType;
    }
    public Item(String itemType, String name, String rarity, float weight, int cost, int amount) {
        this.itemType = itemType;
        this.name = name;
        this.rarity = rarity;
        this.weight = weight;
        this.cost = cost;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
