package com.example.buildingcontrols.models;

public class Apartment extends Room {
    private String ownerName;

    // Constructor
    public Apartment(String id, String ownerName) {
        super(id); // Call the parent Room constructor
        this.ownerName = ownerName;
    }

    // Getter and Setter for ownerName
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    // Override the toString() method for Apartment-specific details
    @Override
    public String toString() {
        return "Apartment{" +
                "id='" + getId() + '\'' +
                ", temperature=" + getTemperature() +
                ", heatingEnabled=" + isHeatingEnabled() +
                ", coolingEnabled=" + isCoolingEnabled() +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
