package com.example.buildingcontrols.controllers;

import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CLITest {
    private CLI cli;
    private BuildingController controller;

    @BeforeEach
    public void setUp() {
        controller = BuildingController.getInstance();
        controller.clearAllBuildings(); // Clear any existing data
        cli = new CLI();
    }

    @Test
    public void testInitializeDefaultBuilding() {
        assertNotNull(controller.getBuildingById("B1"));
        Building defaultBuilding = controller.getBuildingById("B1");
        assertEquals("Default Building", defaultBuilding.getName());
        assertEquals(25.0, defaultBuilding.getRequestedTemperature());
        assertEquals(4, defaultBuilding.getRooms().size());

        Room room1 = defaultBuilding.getRoomById("101");
        assertNotNull(room1);
        assertTrue(room1 instanceof Apartment);
    }
}
