package com.example.buildingcontrols.models;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String id;
    private String name;
    private List<Room> rooms;

    // Constructor
    public Building(String id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
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

    // Calculate total temperature (example functionality)
    public double calculateAverageTemperature() {
        return rooms.stream()
            .mapToDouble(Room::getTemperature)
            .average()
            .orElse(0.0);
    }

    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
