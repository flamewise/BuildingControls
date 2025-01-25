package com.example.buildingcontrols.controllers;

import java.util.Scanner;

public class CLI {
    private boolean running;

    public CLI() {
        this.running = true;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            showMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            handleUserChoice(choice);
        }

        System.out.println("Exiting the application. Goodbye!");
    }

    private void showMenu() {
        System.out.println("\n=== Building Controls Menu ===");
        System.out.println("1. Add Building");
        System.out.println("2. List Buildings");
        System.out.println("3. Add Room/Apartment to Building");
        System.out.println("4. View Building Details");
        System.out.println("5. Exit");
    }

    private void handleUserChoice(String choice) {
        switch (choice) {
            case "1":
                System.out.println("Option to add a building chosen.");
                break;
            case "2":
                System.out.println("Option to list buildings chosen.");
                break;
            case "3":
                System.out.println("Option to add room/apartment chosen.");
                break;
            case "4":
                System.out.println("Option to view building details chosen.");
                break;
            case "5":
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
    
        BuildingController controller = new BuildingController();
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
        BuildingController controller = new BuildingController();
        var buildings = controller.getAllBuildings();
    
        if (buildings.isEmpty()) {
            System.out.println("No buildings found.");
        } else {
            System.out.println("\nList of Buildings:");
            for (Building building : buildings) {
                System.out.println(building);
            }
        }
    }

    private void addRoomToBuilding(Scanner scanner) {
        System.out.print("Enter Building ID: ");
        String buildingId = scanner.nextLine();
    
        BuildingController controller = new BuildingController();
        Building building = controller.getBuildingById(buildingId);
    
        if (building == null) {
            System.out.println("Building not found. Try again.");
            return;
        }
    
        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine();
    
        System.out.print("Enter Room Type (Room/Apartment): ");
        String roomType = scanner.nextLine().toLowerCase();
    
        if (roomType.equals("room")) {
            Room room = new Room(roomId);
            controller.addRoomToBuilding(buildingId, room);
            System.out.println("Room added successfully!");
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
}
