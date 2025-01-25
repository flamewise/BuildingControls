package com.example.buildingcontrols.ui;

import com.example.buildingcontrols.controllers.BuildingController;
import com.example.buildingcontrols.models.Building;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MainWindowTest {
    private MainWindow mainWindow;

    @BeforeEach
    void setUp() {
        // Skip tests if running in headless mode
        assumeTrue(!GraphicsEnvironment.isHeadless(), "Skipping GUI tests in headless mode");

        // Set up the MainWindow instance
        SwingUtilities.invokeLater(() -> mainWindow = new MainWindow());
        // Ensure the MainWindow is fully initialized
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AfterEach
    void tearDown() {
        if (mainWindow != null) {
            SwingUtilities.invokeLater(() -> {
                mainWindow.dispose();
                mainWindow = null;
            });
        }
    }

    @Test
    @DisplayName("Test Add Building")
    void testAddBuilding() {
        BuildingController controller = BuildingController.getInstance();
        SwingUtilities.invokeLater(() -> {
            mainWindow.addBuilding();
            Building building = new Building("B001", "Test Building");
            controller.addBuilding(building);
        });

        assertEquals(1, controller.getAllBuildings().size(), "Building count should be 1");
    }

    @Test
    @DisplayName("Test List Buildings")
    void testListBuildings() {
        BuildingController controller = BuildingController.getInstance();
        Building building = new Building("B002", "Test Office");
        controller.addBuilding(building);

        SwingUtilities.invokeLater(() -> mainWindow.showBuildingSelectionMenu());
        assertEquals(1, controller.getAllBuildings().size(), "Building count should be 1");
    }
}
