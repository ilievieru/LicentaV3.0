package com.Corola.licenta.entities;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by P3700664 on 5/28/2017.
 */
public class Event {
    private String eventId;
    private String eventName;
    private String eventDescriotion;
    private List<User> userList;
    private List<String> dates;

    public Event() {
    }

    public Event(String eventId, String eventName, String eventDescription, List<User> userList, List<String> dates) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescriotion = eventDescription;
        this.userList = userList;
        this.dates = dates;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescriotion() {
        return eventDescriotion;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDescriotion(String eventDescriotion) {
        this.eventDescriotion = eventDescriotion;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public void afisare() {
        System.out.println("EventId: " + eventId + "\n"
                + "Event Name: " + eventName + "\n"
                + "Event Description: " + eventDescriotion);
        System.out.println("----------------Users-----------------");
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).afisare();
        }
        System.out.println("----------------Dates------------------");
        for (int i = 0; i < dates.size(); i++) {
            System.out.println("Date: " + dates.get(i));
        }
    }
}
