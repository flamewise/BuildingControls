package com.example.buildingcontrols.models;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String id;
    private String name;
    private List<Room> rooms;
    private double requestedTemperature;

    // Constructor
    public Building(String id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
        this.requestedTemperature = 20.0; // Default requested temperature
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public double getRequestedTemperature() {
        return requestedTemperature;
    }

    public void setRequestedTemperature(double requestedTemperature) {
        this.requestedTemperature = requestedTemperature;
    }

    // Methods to manage rooms
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room getRoomById(String roomId) {
        return rooms.stream()
                .filter(room -> room.getId().equals(roomId))
                .findFirst()
                .orElse(null);
    }

    public void removeRoom(String roomId) {
        rooms.removeIf(room -> room.getId().equals(roomId));
    }

    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", requestedTemperature=" + requestedTemperature +
                ", rooms=" + rooms +
                '}';
    }
}
