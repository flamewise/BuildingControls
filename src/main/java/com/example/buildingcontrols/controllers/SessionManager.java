package com.example.buildingcontrols.controllers;

import com.example.buildingcontrols.models.Building;

public class SessionManager {
    private Building currentBuilding;

    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(Building building) {
        this.currentBuilding = building;
    }

    public void clearSession() {
        this.currentBuilding = null;
    }
}
