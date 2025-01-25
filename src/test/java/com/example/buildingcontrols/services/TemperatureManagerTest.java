package com.example.buildingcontrols.services;

import com.example.buildingcontrols.controllers.BuildingController;
import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureManagerTest {
    private TemperatureManager temperatureManager;
    private BuildingController buildingController;

    @BeforeEach
    void setUp() {
        buildingController = BuildingController.getInstance();
        temperatureManager = new TemperatureManager();

        // Clear previous data
        buildingController.getAllBuildings().clear();

        // Set up a building with rooms
        Building building = new Building("B001", "Main Building");
        Room room1 = new Room("R001");
        Room room2 = new Room("R002");

        // Set initial temperatures for deterministic testing
        room1.setTemperature(18.0); // Below requested temperature
        room2.setTemperature(22.0); // Above requested temperature

        building.addRoom(room1);
        building.addRoom(room2);
        building.setRequestedTemperature(20.0);

        buildingController.addBuilding(building);
    }

    @Test
    void testHeatingAndCoolingUpdates() {
        // Start temperature manager
        temperatureManager.start();

        // Simulate a single temperature update
        temperatureManager.updateTemperatures();

        Building building = buildingController.getBuildingById("B001");
        assertNotNull(building);

        Room room1 = building.getRoomById("R001");
        Room room2 = building.getRoomById("R002");

        // Room 1 should have heating enabled and temperature slightly increased
        assertTrue(room1.isHeatingEnabled());
        assertFalse(room1.isCoolingEnabled());
        assertEquals(18.5, room1.getTemperature(), 1);

        // Room 2 should have cooling enabled and temperature slightly decreased
        assertFalse(room2.isHeatingEnabled());
        assertTrue(room2.isCoolingEnabled());
        assertEquals(21.5, room2.getTemperature(), 1);

        // Stop temperature manager
        temperatureManager.stop();
    }

    @Test
    void testNoHeatingOrCoolingWithinTolerance() {
        Building building = buildingController.getBuildingById("B001");
        assertNotNull(building);

        Room room3 = new Room("R003");
        room3.setTemperature(20.2); // Within ±0.5 of requested temperature
        building.addRoom(room3);

        temperatureManager.updateTemperatures();

        // Room 3 should have neither heating nor cooling enabled
        assertFalse(room3.isHeatingEnabled());
        assertFalse(room3.isCoolingEnabled());
        assertEquals(20.2, room3.getTemperature(), 1);
    }

    @Test
    void testRandomDriftWhenNoHeatingOrCooling() {
        Building building = buildingController.getBuildingById("B001");
        assertNotNull(building);

        Room room3 = new Room("R003");
        room3.setTemperature(20.2); // Within ±0.5 of requested temperature
        building.addRoom(room3);

        // Simulate random drift over multiple updates
        for (int i = 0; i < 5; i++) {
            temperatureManager.updateTemperatures();
        }

        // Room 3 should still have neither heating nor cooling enabled
        assertFalse(room3.isHeatingEnabled());
        assertFalse(room3.isCoolingEnabled());

        // The temperature should drift but remain close to the initial value
        double temperature = room3.getTemperature();
        assertTrue(19.7 <= temperature && temperature <= 20.7, "Temperature drifted out of bounds");
    }
}
