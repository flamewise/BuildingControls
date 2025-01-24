package com.example.buildingcontrols.models;

public class CommonRoom extends Room {
    private int capacity; // The maximum number of people the common room can accommodate
    private CommonRoomType type; // The type of the common room (e.g., Gym, Library, Laundry)

    // Constructor
    public CommonRoom(String id, int capacity, CommonRoomType type) {
        super(id); // Call the parent Room constructor
        this.capacity = capacity;
        this.type = type;
    }

    // Getters and Setters for capacity and type
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public CommonRoomType getType() {
        return type;
    }

    public void setType(CommonRoomType type) {
        this.type = type;
    }

    // Override the toString() method to include capacity and type
    @Override
    public String toString() {
        return "CommonRoom{" +
                "id='" + getId() + '\'' +
                ", temperature=" + getTemperature() +
                ", heatingEnabled=" + isHeatingEnabled() +
                ", coolingEnabled=" + isCoolingEnabled() +
                ", capacity=" + capacity +
                ", type=" + type +
                '}';
    }
}
