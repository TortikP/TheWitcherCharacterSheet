package com.example.thewitchercharachtersheet.adapters.objects;

public class CharacterElement {
    private String characterName;
    private String characterOccupation;
    private String characterRace;
    public CharacterElement()
    {

    }
    public CharacterElement(String characterName, String characterOccupation, String characterRace)
    {
        this.characterName = characterName;
        this.characterOccupation = characterOccupation;
        this.characterRace = characterRace;
    }
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterOccupation() {
        return characterOccupation;
    }

    public void setCharacterOccupation(String characterOccupation) {
        this.characterOccupation = characterOccupation;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
    }

}
