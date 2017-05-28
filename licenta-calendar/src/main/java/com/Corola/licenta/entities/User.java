package com.Corola.licenta.entities;

import java.util.List;

/**
 * Created by P3700664 on 5/28/2017.
 */
public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String securityLevel;
    private List<String> events;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public List<String> getEvents() {

        return events;
    }

    public User() {
    }

    public User(String userName, String firstName, String lastName, String password, String securityLevel, List<String> events) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.securityLevel = securityLevel;
        this.events = events;
    }

    public void afisare() {
        System.out.println("User Name " + userName +"\n"
                +"First Name " + firstName + "\n"
                + "Last name " + lastName + "\n"
                + "Security Level " + securityLevel + "\n"
                + "Password " + password);
        for (int i = 0; i < events.size(); i++) {
            System.out.println("Event " + events.get(i));
        }
    }
}
