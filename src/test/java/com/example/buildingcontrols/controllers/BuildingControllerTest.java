package com.example.buildingcontrols.controllers;

import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.Room;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuildingControllerTest {

    @Test
    public void testAddBuilding() {
        BuildingController controller = new BuildingController();
        Building building = new Building("B1", "Main Building");
        controller.addBuilding(building);

        List<Building> buildings = controller.getAllBuildings();
        assertEquals(1, buildings.size());
        assertEquals("Main Building", buildings.get(0).getName());
    }

    @Test
    public void testAddRoomToBuilding() {
        BuildingController controller = new BuildingController();
        Building building = new Building("B1", "Main Building");
        controller.addBuilding(building);

        Room room = new Room("R1");
        controller.addRoomToBuilding("B1", room);

        List<Room> rooms = controller.getRoomsInBuilding("B1");
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
        assertEquals("R1", rooms.get(0).getId());
    }
}
