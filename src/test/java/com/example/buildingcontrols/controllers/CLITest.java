package com.example.buildingcontrols.controllers;

import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.CommonRoom;
import com.example.buildingcontrols.models.CommonRoomType;
import com.example.buildingcontrols.models.Room;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {
    private BuildingController controller;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        controller = BuildingController.getInstance(); // Singleton instance
        controller.getAllBuildings().clear(); // Clear any previous state
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    @DisplayName("Test Adding a New Building")
    void testAddBuilding() {
        String input = "1\nB001\nOffice Building\n6\n"; // Add Building, then Exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        cli.start();

        assertEquals(1, controller.getAllBuildings().size());
        Building building = controller.getBuildingById("B001");
        assertNotNull(building);
        assertEquals("Office Building", building.getName());
    }

    @Test
    @DisplayName("Test Listing Buildings")
    void testListBuildings() {
        controller.addBuilding(new Building("B001", "Office Building"));
        controller.addBuilding(new Building("B002", "Residential Tower"));

        String input = "2\n6\n"; // List Buildings, then Exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        cli.start();

        String output = outContent.toString();
        assertTrue(output.contains("B001"));
        assertTrue(output.contains("B002"));
    }

    @Test
    @DisplayName("Test Adding Rooms to a Building")
    void testAddRoomToBuilding() {
        controller.addBuilding(new Building("B001", "Office Building"));

        String input = "3\nB001\nA101\napartment\nJohn Doe\n6\n"; // Add Room, then Exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        cli.start();

        Building building = controller.getBuildingById("B001");
        assertNotNull(building);
        assertEquals(1, building.getRooms().size());
        Room room = building.getRooms().get(0);
        assertTrue(room instanceof Apartment);
        assertEquals("A101", room.getId());
        assertEquals("John Doe", ((Apartment) room).getOwnerName());
    }

    @Test
    @DisplayName("Test Viewing Building Details")
    void testViewBuildingDetails() {
        Building building = new Building("B001", "Office Building");
        building.addRoom(new Apartment("A101", "John Doe"));
        building.addRoom(new CommonRoom("CR201", CommonRoomType.GYM));
        controller.addBuilding(building);

        String input = "4\nB001\n6\n"; // View Building Details, then Exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        cli.start();

        String output = outContent.toString();
        assertTrue(output.contains("Building ID: B001"));
        assertTrue(output.contains("Building Name: Office Building"));
        assertTrue(output.contains("Apartment{id='A101'"));
        assertTrue(output.contains("CommonRoom{id='CR201'"));
    }

    @Test
    @DisplayName("Test Adjusting Building Temperature")
    void testAdjustBuildingTemperature() {
        Building building = new Building("B001", "Office Building");
        controller.addBuilding(building);

        String input = "5\nB001\n22.5\n6\n"; // Adjust Temperature, then Exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        cli.start();

        assertEquals(22.5, building.getRequestedTemperature());
    }

    @Test
    @DisplayName("Test Handling Invalid Inputs")
    void testInvalidInputs() {
        String input = "3\nINVALID\n6\n"; // Try to add room to non-existent building, then Exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        cli.start();

        String output = outContent.toString();
        assertTrue(output.contains("Building not found. Try again."));
    }

    @Test
    @DisplayName("Test Duplicate Building IDs")
    void testDuplicateBuildingIds() {
        controller.addBuilding(new Building("B001", "Office Building"));

        String input = "1\nB001\nDuplicate Building\n6\n"; // Try to add a building with duplicate ID, then Exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        cli.start();

        String output = outContent.toString();
        assertTrue(output.contains("Building ID already exists. Try again."));
    }
}
