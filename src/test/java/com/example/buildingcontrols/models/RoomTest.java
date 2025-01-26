package com.example.buildingcontrols.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testRoomCreation() {
        // Arrange
        String roomId = "Room1";

        // Act
        Room room = new Room(roomId);

        // Assert
        assertEquals(roomId, room.getId());
        assertTrue(room.getTemperature() >= 10.0 && room.getTemperature() <= 40.0,
            "Temperature should be initialized to a random value between 10 and 40.");
        assertFalse(room.isHeatingEnabled());
        assertFalse(room.isCoolingEnabled());
        assertEquals(1, room.getTemperatureHistory().size(),
            "Temperature history should initially contain one record.");
    }

    @Test
    void testSetTemperature() {
        // Arrange
        Room room = new Room("Room1");

        // Act
        room.setTemperature(25.0);

        // Assert
        assertEquals(25.0, room.getTemperature());
        assertEquals(2, room.getTemperatureHistory().size(),
            "Temperature history should contain a new record after setting temperature.");
        assertEquals(25.0, room.getTemperatureHistory().get(1),
            "The latest temperature record should match the newly set temperature.");
    }

    @Test
    void testGetTemperatureHistory() {
        // Arrange
        Room room = new Room("Room1");
        room.setTemperature(20.0);
        room.setTemperature(22.0);

        // Act
        List<Double> temperatureHistory = room.getTemperatureHistory();

        // Assert
        assertEquals(3, temperatureHistory.size(),
            "Temperature history should contain all recorded temperatures.");
        assertEquals(20.0, temperatureHistory.get(1));
        assertEquals(22.0, temperatureHistory.get(2));
    }

    @Test
    void testUpdateHeatingCoolingStates() {
        // Arrange
        Room room = new Room("Room1");
        room.setTemperature(18.0);

        // Act & Assert
        room.updateHeatingCoolingStates(20.0); // Requested temp is higher
        assertTrue(room.isHeatingEnabled());
        assertFalse(room.isCoolingEnabled());

        room.updateHeatingCoolingStates(16.0); // Requested temp is lower
        assertFalse(room.isHeatingEnabled());
        assertTrue(room.isCoolingEnabled());

        room.updateHeatingCoolingStates(18.0); // Within tolerance
        assertFalse(room.isHeatingEnabled());
        assertFalse(room.isCoolingEnabled());
    }

    @Test
    void testToString() {
        // Arrange
        Room room = new Room("Room1");

        // Act
        String result = room.toString();

        // Assert
        assertTrue(result.contains("id='Room1'"));
        assertTrue(result.contains("temperature="));
    }
}
