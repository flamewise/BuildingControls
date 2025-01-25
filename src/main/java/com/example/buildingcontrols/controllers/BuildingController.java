package com.example.buildingcontrols.controllers;

import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.Room;

import java.util.ArrayList;
import java.util.List;

public class BuildingController {
    public static BuildingController instance; // Singleton instance
    public List<Building> buildings;

    // Private constructor to prevent instantiation
    public BuildingController() {
        this.buildings = new ArrayList<>();
    }

    // Public method to get the Singleton instance
    public static synchronized BuildingController getInstance() {
        if (instance == null) {
            instance = new BuildingController();
        }
        return instance;
    }

    // Add a new building
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    // List all buildings
    public List<Building> getAllBuildings() {
        return buildings;
    }

    // Find a building by ID
    public Building getBuildingById(String buildingId) {
        return buildings.stream()
            .filter(b -> b.getId().equals(buildingId))
            .findFirst()
            .orElse(null);
    }

    // Add a room to a specific building
    public void addRoomToBuilding(String buildingId, Room room) {
        Building building = getBuildingById(buildingId);
        if (building != null) {
            building.addRoom(room);
        }
    }

    // List rooms of a specific building
    public List<Room> getRoomsInBuilding(String buildingId) {
        Building building = getBuildingById(buildingId);
        return building != null ? building.getRooms() : null;
    }
}
