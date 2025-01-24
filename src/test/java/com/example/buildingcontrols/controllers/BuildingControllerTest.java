package com.example.buildingcontrols.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildingControllerTest {
    @Test
    public void testManageBuilding() {
        BuildingController controller = new BuildingController();
        controller.manageBuilding();
        assertTrue(true); // Placeholder test
    }
}
