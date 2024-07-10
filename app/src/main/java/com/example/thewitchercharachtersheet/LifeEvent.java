package com.example.thewitchercharachtersheet;

public class LifeEvent {

    int timePeriod;
    String eventType;
    String eventDescription;
    LifeEvent(int timePeriod, String eventType, String eventDescription)
    {
        this.timePeriod = timePeriod;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

}
