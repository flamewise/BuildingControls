package com.example.buildingcontrols.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {

    @Test
    public void testBuildingInitialization() {
        Building building = new Building("B1", "Test Building");

        assertEquals("B1", building.getId());
        assertEquals("Test Building", building.getName());
        assertEquals(20.0, building.getRequestedTemperature());
        assertTrue(building.getRooms().isEmpty());
    }

    @Test
    public void testAddRoom() {
        Building building = new Building("B1", "Test Building");

        Room room = new Room("R1");
        building.addRoom(room);

        List<Room> rooms = building.getRooms();
        assertEquals(1, rooms.size());
        assertEquals("R1", rooms.get(0).getId());
    }

    @Test
    public void testGetRoomById() {
        Building building = new Building("B1", "Test Building");

        Room room1 = new Room("R1");
        Room room2 = new Room("R2");
        building.addRoom(room1);
        building.addRoom(room2);

        Room foundRoom = building.getRoomById("R2");
        assertNotNull(foundRoom);
        assertEquals("R2", foundRoom.getId());

        assertNull(building.getRoomById("R3")); // Non-existent room
    }

    @Test
    public void testRemoveRoom() {
        Building building = new Building("B1", "Test Building");

        Room room1 = new Room("R1");
        Room room2 = new Room("R2");
        building.addRoom(room1);
        building.addRoom(room2);

        building.removeRoom("R1");

        List<Room> rooms = building.getRooms();
        assertEquals(1, rooms.size());
        assertEquals("R2", rooms.get(0).getId());
    }

    @Test
    public void testSetRequestedTemperature() {
        Building building = new Building("B1", "Test Building");

        building.setRequestedTemperature(22.5);
        assertEquals(22.5, building.getRequestedTemperature());
    }

    @Test
    public void testToString() {
        Building building = new Building("B1", "Test Building");

        Room room = new Room("R1");
        building.addRoom(room);

        String expected = "Building{id='B1', name='Test Building', requestedTemperature=20.0, rooms=[Room{id='R1', temperature=";
        assertTrue(building.toString().contains(expected));
    }
}
