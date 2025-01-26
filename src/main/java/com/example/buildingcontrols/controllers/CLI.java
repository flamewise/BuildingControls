package com.example.buildingcontrols.controllers;

import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.CommonRoom;
import com.example.buildingcontrols.models.CommonRoomType;
import com.example.buildingcontrols.models.Room;
import com.example.buildingcontrols.services.TemperatureManager;

import java.util.Scanner;

public class CLI {
    private boolean running;
    private final BuildingController controller;
    private final TemperatureManager temperatureManager;
    private final SessionManager sessionManager;

    public CLI() {
        this.running = true;
        this.controller = BuildingController.getInstance(); // Singleton instance
        this.temperatureManager = new TemperatureManager(); // Initialize TemperatureManager
        this.sessionManager = new SessionManager(); // Initialize SessionManager
        this.temperatureManager.start(); // Start the background temperature management

        initializeDefaultBuilding(); // Initialize default settings
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            showMainMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            handleMainMenuChoice(choice, scanner);
        }

        System.out.println("Exiting the application. Goodbye!");
        this.temperatureManager.stop(); // Stop the background temperature management
        scanner.close();
    }

    private void showMainMenu() {
        System.out.println("\n=== Building Controls Main Menu ===");
        System.out.println("1. Add Building");
        System.out.println("2. List Buildings and Select");
        System.out.println("3. Exit");
    }

    private void handleMainMenuChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1":
                addBuilding(scanner);
                break;
            case "2":
                listBuildingsAndSelect(scanner);
                break;
            case "3":
                running = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void listBuildingsAndSelect(Scanner scanner) {
        var buildings = controller.getAllBuildings();

        if (buildings.isEmpty()) {
            System.out.println("No buildings found.");
        } else {
            System.out.println("\nList of Buildings:");
            for (int i = 0; i < buildings.size(); i++) {
                Building building = buildings.get(i);
                System.out.println((i + 1) + ". " + building.getId() + " - " + building.getName());
            }

            System.out.print("Select a building by number (or 0 to go back): ");
            int selection;

            try {
                selection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
                return;
            }

            if (selection == 0) {
                return; // Go back to main menu
            }

            if (selection < 1 || selection > buildings.size()) {
                System.out.println("Invalid selection. Please try again.");
                return;
            }

            Building selectedBuilding = buildings.get(selection - 1);
            sessionManager.setCurrentBuilding(selectedBuilding);
            showBuildingMenu(scanner);
        }
    }

    private void showBuildingMenu(Scanner scanner) {
        while (true) {
            Building currentBuilding = sessionManager.getCurrentBuilding();
            if (currentBuilding == null) {
                System.out.println("No building selected. Returning to Main Menu...");
                return;
            }
    
            // Print out room details
            System.out.println("\n=== Building Menu ===");
            System.out.println("Building Name: " + currentBuilding.getName());
            System.out.println("Requested Temperature: " + currentBuilding.getRequestedTemperature() + "°C");
            System.out.println("\nRooms and Details:");
            for (Room room : currentBuilding.getRooms()) {
                System.out.println("Room ID: " + room.getId());
                System.out.println("  Temperature: " + String.format("%.1f°C", room.getTemperature()));
                System.out.println("  Heater Enabled: " + room.isHeatingEnabled());
                System.out.println("  Cooler Enabled: " + room.isCoolingEnabled());
            }
    
            // Display building menu options
            System.out.println("\n1. Add Room/Apartment");
            System.out.println("2. Adjust Building Temperature");
            System.out.println("3. Recalculate Heating/Cooling States");
            System.out.println("4. Return to Main Menu");
    
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1":
                    addRoomToBuilding(scanner);
                    break;
                case "2":
                    adjustBuildingTemperature(scanner);
                    break;
                case "3":
                    recalculateHeatingCoolingStates();
                    break;
                case "4":
                    sessionManager.clearSession(); // Clear the selected building session
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void addBuilding(Scanner scanner) {
        System.out.print("Enter Building ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Building Name: ");
        String name = scanner.nextLine();

        Building existingBuilding = controller.getBuildingById(id);

        if (existingBuilding != null) {
            System.out.println("Building ID already exists. Try again.");
            return;
        }

        Building newBuilding = new Building(id, name);
        controller.addBuilding(newBuilding);
        System.out.println("Building added successfully!");
    }

    private void addRoomToBuilding(Scanner scanner) {
        Building currentBuilding = sessionManager.getCurrentBuilding();

        if (currentBuilding == null) {
            System.out.println("No building selected. Please select a building first.");
            return;
        }

        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine();

        System.out.print("Enter Room Type (CommonRoom/Apartment): ");
        String roomType = scanner.nextLine().toLowerCase();

        if (roomType.equals("commonroom")) {
            System.out.print("Enter Common Room Type (GYM/LIBRARY/LAUNDRY): ");
            String commonRoomType = scanner.nextLine().toUpperCase();
            CommonRoomType type;

            try {
                type = CommonRoomType.valueOf(commonRoomType);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Common Room Type. Try again.");
                return;
            }

            CommonRoom commonRoom = new CommonRoom(roomId, type);
            currentBuilding.addRoom(commonRoom);
            System.out.println("Common Room added successfully!");
        } else if (roomType.equals("apartment")) {
            System.out.print("Enter Owner Name: ");
            String ownerName = scanner.nextLine();

            Apartment apartment = new Apartment(roomId, ownerName);
            currentBuilding.addRoom(apartment);
            System.out.println("Apartment added successfully!");
        } else {
            System.out.println("Invalid room type. Try again.");
        }
    }

    private void adjustBuildingTemperature(Scanner scanner) {
        Building currentBuilding = sessionManager.getCurrentBuilding();

        if (currentBuilding == null) {
            System.out.println("No building selected. Please select a building first.");
            return;
        }

        System.out.println("Current requested temperature: " + currentBuilding.getRequestedTemperature());
        System.out.print("Enter new requested temperature: ");
        double newTemperature;

        try {
            newTemperature = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid temperature value. Please try again.");
            return;
        }

        currentBuilding.setRequestedTemperature(newTemperature);
        System.out.println("Requested temperature updated successfully!");
    }

    private void recalculateHeatingCoolingStates() {
        Building currentBuilding = sessionManager.getCurrentBuilding();

        if (currentBuilding == null) {
            System.out.println("No building selected. Please select a building first.");
            return;
        }

        for (Room room : currentBuilding.getRooms()) {
            room.updateHeatingCoolingStates(currentBuilding.getRequestedTemperature());
        }
        System.out.println("Heating/Cooling states recalculated successfully!");
    }

    private void initializeDefaultBuilding() {
        System.out.println("Initializing default building and rooms...");

        Building defaultBuilding = new Building("B1", "Default Building");
        defaultBuilding.setRequestedTemperature(25.0);

        // Adding apartments
        defaultBuilding.addRoom(new Apartment("101", "John Doe"));
        defaultBuilding.addRoom(new Apartment("102", "Jane Doe"));

        // Adding common rooms
        defaultBuilding.addRoom(new CommonRoom("Gym", CommonRoomType.GYM));
        defaultBuilding.addRoom(new CommonRoom("Library", CommonRoomType.LIBRARY));

        // Setting initial heating/cooling states
        for (Room room : defaultBuilding.getRooms()) {
            room.updateHeatingCoolingStates(defaultBuilding.getRequestedTemperature());
        }

        controller.addBuilding(defaultBuilding);
        System.out.println("Default building and rooms initialized.");
    }
}
