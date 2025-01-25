package com.example.buildingcontrols.models;

public class CommonRoom extends Room {
    private CommonRoomType type; // The type of the common room (e.g., Gym, Library, Laundry)

    // Constructor
    public CommonRoom(String id,  CommonRoomType type) {
        super(id); // Call the parent Room constructor
        this.type = type;
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
                ", type=" + type +
                '}';
    }
}
