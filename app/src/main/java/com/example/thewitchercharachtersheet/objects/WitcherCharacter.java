package com.example.thewitchercharachtersheet.objects;

import com.example.thewitchercharachtersheet.LifeEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WitcherCharacter implements Serializable {
    String name;
    Occupation occupation;
    List<Item> startingEquipment;
    Race race;
    String gender;
    int age;
    String birthRegion;
    String birthplace;
    int birthplaceBonus;
    String familyStatus;
    String parentsStatus;
    String familyOrigin;
    private List<Feature> stats;
    private List<Feature> additionalStats;
    private HashMap<String, List<Ability>> abilities;
    ArrayList<LifeEvent> lifeEvents;
    int currentMoney;
    public WitcherCharacter()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public List<Item> getStartingEquipment() {
        return startingEquipment;
    }

    public void setStartingEquipment(List<Item> startingEquipment) {
        this.startingEquipment = startingEquipment;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public int getBirthplaceBonus() {
        return birthplaceBonus;
    }

    public void setBirthplaceBonus(int birthplaceBonus) {
        this.birthplaceBonus = birthplaceBonus;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getParentsStatus() {
        return parentsStatus;
    }

    public void setParentsStatus(String parentsStatus) {
        this.parentsStatus = parentsStatus;
    }

    public String getFamilyOrigin() {
        return familyOrigin;
    }

    public void setFamilyOrigin(String familyOrigin) {
        this.familyOrigin = familyOrigin;
    }

    public ArrayList<LifeEvent> getLifeEvents() {
        return lifeEvents;
    }

    public void setLifeEvents(ArrayList<LifeEvent> lifeEvents) {
        this.lifeEvents = lifeEvents;
    }
    public String getBirthRegion() {
        return birthRegion;
    }

    public void setBirthRegion(String birthRegion) {
        this.birthRegion = birthRegion;
    }

    public List<Feature> getStats() {
        return stats;
    }

    public void setStats(List<Feature> stats) {
        this.stats = stats;
    }

    public List<Feature> getAdditionalStats() {
        return additionalStats;
    }

    public void setAdditionalStats(List<Feature> additionalStats) {
        this.additionalStats = additionalStats;
    }

    public HashMap<String, List<Ability>> getAbilities() {
        return abilities;
    }

    public void setAbilities(HashMap<String, List<Ability>> abilities) {
        this.abilities = abilities;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }
}
