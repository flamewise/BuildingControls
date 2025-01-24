package com.example.buildingcontrols.models;

import java.util.Random;

public class Room {
    private String id;
    private double temperature; // Default random value between 10 and 40
    private boolean heatingEnabled;
    private boolean coolingEnabled;

    // Constructor
    public Room(String id) {
        this.id = id;
        this.temperature = generateRandomTemperature(10, 40);
        this.heatingEnabled = false;
        this.coolingEnabled = false;
    }

    // Generate a random temperature between min and max
    private double generateRandomTemperature(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isHeatingEnabled() {
        return heatingEnabled;
    }

    public void enableHeating() {
        this.heatingEnabled = true;
        this.coolingEnabled = false; // Disable cooling if heating is enabled
    }

    public void disableHeating() {
        this.heatingEnabled = false;
    }

    public boolean isCoolingEnabled() {
        return coolingEnabled;
    }

    public void enableCooling() {
        this.coolingEnabled = true;
        this.heatingEnabled = false; // Disable heating if cooling is enabled
    }

    public void disableCooling() {
        this.coolingEnabled = false;
    }

    // toString() Method for Debugging
    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", temperature=" + temperature +
                ", heatingEnabled=" + heatingEnabled +
                ", coolingEnabled=" + coolingEnabled +
                '}';
    }
}
