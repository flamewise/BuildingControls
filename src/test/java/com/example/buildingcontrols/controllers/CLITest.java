package com.example.buildingcontrols.controllers;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {
    @Test
    void testAddRoomToBuilding() {
        String input = "1\nB1\nBuilding One\n3\nB1\nR1\nApartment\nJohn Doe\n6\n"; // Add a building, add a room, and exit
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CLI cli = new CLI();
        cli.start();

        String output = outputStream.toString();
        assertTrue(output.contains("Apartment added successfully!"), "Apartment should be added.");
    }

    @Test
    void testAdjustBuildingTemperature() {
        String input = "1\nB1\nBuilding One\n5\nB1\n22.5\n6\n"; // Add a building, adjust temperature, and exit
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CLI cli = new CLI();
        cli.start();

        String output = outputStream.toString();
        assertTrue(output.contains("Requested temperature updated successfully!"), "Temperature should be updated.");
    }
}
