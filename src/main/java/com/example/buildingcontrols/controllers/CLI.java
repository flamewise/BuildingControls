package com.example.buildingcontrols.controllers;

import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.Room;
import com.example.buildingcontrols.models.CommonRoomType;
import com.example.buildingcontrols.models.CommonRoom;
import com.example.buildingcontrols.services.TemperatureManager;

import java.util.Scanner;

public class CLI {
    private boolean running;
    private final BuildingController controller;
    private final TemperatureManager temperatureManager;

    public CLI() {
        this.running = true;
        this.controller = BuildingController.getInstance(); // Singleton instance
        this.temperatureManager = new TemperatureManager(); // Initialize the TemperatureManager
        this.temperatureManager.start(); // Start the background temperature management
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            showMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            handleUserChoice(choice, scanner);
        }

        System.out.println("Exiting the application. Goodbye!");
        this.temperatureManager.stop(); // Stop the background temperature management
        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n=== Building Controls Menu ===");
        System.out.println("1. Add Building");
        System.out.println("2. List Buildings");
        System.out.println("3. Add Room/Apartment to Building");
        System.out.println("4. View Building Details");
        System.out.println("5. Adjust Building Temperature");
        System.out.println("6. Exit");
    }

    private void handleUserChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1":
                addBuilding(scanner);
                break;
            case "2":
                listBuildings();
                break;
            case "3":
                addRoomToBuilding(scanner);
                break;
            case "4":
                viewBuildingDetails(scanner);
                break;
            case "5":
                adjustBuildingTemperature(scanner);
                break;
            case "6":
                running = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
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

    private void listBuildings() {
        var buildings = controller.getAllBuildings();

        if (buildings.isEmpty()) {
            System.out.println("No buildings found.");
        } else {
            System.out.println("\nList of Buildings:");
            for (Building building : buildings) {
                System.out.println(building + " | Requested Temp: " + building.getRequestedTemperature() + "°C");
            }
        }
    }

    private void addRoomToBuilding(Scanner scanner) {
        System.out.print("Enter Building ID: ");
        String buildingId = scanner.nextLine();
    
        Building building = controller.getBuildingById(buildingId);
    
        if (building == null) {
            System.out.println("Building not found. Try again.");
            return;
        }
    
        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine();
    
        System.out.print("Enter Room Type (CommonRoom/Apartment): "); // Updated prompt
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
            controller.addRoomToBuilding(buildingId, commonRoom);
            System.out.println("Common Room added successfully!");
        } else if (roomType.equals("apartment")) {
            System.out.print("Enter Owner Name: ");
            String ownerName = scanner.nextLine();
    
            Apartment apartment = new Apartment(roomId, ownerName);
            controller.addRoomToBuilding(buildingId, apartment);
            System.out.println("Apartment added successfully!");
        } else {
            System.out.println("Invalid room type. Try again.");
        }
    }

    private void viewBuildingDetails(Scanner scanner) {
        System.out.print("Enter Building ID: ");
        String buildingId = scanner.nextLine();
    
        Building building = controller.getBuildingById(buildingId);
    
        if (building == null) {
            System.out.println("Building not found. Try again.");
            return;
        }
    
        System.out.println("\n=== Building Details ===");
        System.out.println("Building ID: " + building.getId());
        System.out.println("Building Name: " + building.getName());
        System.out.println("Requested Temperature: " + building.getRequestedTemperature() + "°C");
    
        var rooms = building.getRooms();
    
        if (rooms.isEmpty()) {
            System.out.println("No rooms or apartments in this building.");
        } else {
            System.out.println("\nRooms and Apartments:");
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
    }

    private void adjustBuildingTemperature(Scanner scanner) {
        System.out.print("Enter Building ID: ");
        String buildingId = scanner.nextLine();
    
        Building building = controller.getBuildingById(buildingId);
    
        if (building == null) {
            System.out.println("Building not found. Try again.");
            return;
        }
    
        System.out.println("Current requested temperature: " + building.getRequestedTemperature());
        System.out.print("Enter new requested temperature: ");
        double newTemperature;
    
        try {
            newTemperature = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid temperature value. Try again.");
            return;
        }
    
        building.setRequestedTemperature(newTemperature);
        System.out.println("Requested temperature updated successfully!");
    }
}