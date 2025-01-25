package com.example.buildingcontrols.models;

import java.util.Random;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Room {
    private String id;
    private double temperature; // Default random value between 10 and 40
    private boolean heatingEnabled;
    private boolean coolingEnabled;
    private List<Double> temperatureHistory;

    // Constructor
    public Room(String id) {
        this.id = id;
        this.temperature = generateRandomTemperature(10, 40);
        this.heatingEnabled = false;
        this.coolingEnabled = false;
        this.temperatureHistory = new ArrayList<>();
        recordTemperature();
    }

    // Record the current temperature in the history
    private void recordTemperature() {
        temperatureHistory.add(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        recordTemperature();
    }

    public List<Double> getTemperatureHistory() {
        return new ArrayList<>(temperatureHistory); // Return a copy to prevent external modification
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

    public boolean isHeatingEnabled() {
        return heatingEnabled;
    }

    public boolean isCoolingEnabled() {
        return coolingEnabled;
    }

    /**
     * Updates the heating and cooling states based on the requested temperature.
     * Heating and cooling are not triggered if the temperature is within ±0.5 degrees of the requested temperature.
     *
     * @param requestedTemperature The temperature requested for the building.
     */
    public void updateHeatingCoolingStates(double requestedTemperature) {
        double tolerance = 0.5;
        if (temperature < requestedTemperature - tolerance) {
            heatingEnabled = true;
            coolingEnabled = false;
        } else if (temperature > requestedTemperature + tolerance) {
            coolingEnabled = true;
            heatingEnabled = false;
        } else {
            heatingEnabled = false;
            coolingEnabled = false; // Within tolerance, neither heating nor cooling
        }
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
