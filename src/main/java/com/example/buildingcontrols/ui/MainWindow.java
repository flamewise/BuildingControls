package com.example.buildingcontrols.ui;

import com.example.buildingcontrols.controllers.BuildingController;
import com.example.buildingcontrols.models.Building;
import com.example.buildingcontrols.models.Room;
import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.CommonRoom;
import com.example.buildingcontrols.models.CommonRoomType;
import com.example.buildingcontrols.services.TemperatureManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {
    private final BuildingController controller;
    private final TemperatureManager temperatureManager; // Background temperature management
    private Building currentBuilding;
    private Timer roomUpdateTimer;

    public MainWindow() {
        controller = BuildingController.getInstance();
        temperatureManager = new TemperatureManager(); // Initialize TemperatureManager
        temperatureManager.start(); // Start background temperature management
        initializeUI();
        initializeDefaultBuilding(); // Add default setup
    }

    private void initializeUI() {
        setTitle("Building Controls");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ensure the background thread stops gracefully on exit
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                temperatureManager.stop(); // Stop TemperatureManager
            }
        });

        showMainMenu();
    }

    private void initializeDefaultBuilding() {
        // Create a default building
        Building defaultBuilding = new Building("B1", "Default Building");
        defaultBuilding.setRequestedTemperature(25.0);

        // Add 2 apartments
        defaultBuilding.addRoom(new Apartment("101", "John Doe"));
        defaultBuilding.addRoom(new Apartment("102", "Jane Doe"));

        // Add a gym and a library
        defaultBuilding.addRoom(new CommonRoom("Gym", CommonRoomType.GYM));
        defaultBuilding.addRoom(new CommonRoom("Library", CommonRoomType.LIBRARY));

        // Calculate initial heating and cooling states for all rooms
        for (Room room : defaultBuilding.getRooms()) {
            room.updateHeatingCoolingStates(defaultBuilding.getRequestedTemperature());
        }

        // Add the building to the controller
        controller.addBuilding(defaultBuilding);
    }

    private void showMainMenu() {
        getContentPane().removeAll();
        setLayout(new GridLayout(4, 1));

        JButton addBuildingButton = new JButton("Add Building");
        JButton listBuildingsButton = new JButton("List Buildings and Select");
        JButton exitButton = new JButton("Exit");

        addBuildingButton.addActionListener(e -> addBuilding());
        listBuildingsButton.addActionListener(e -> showBuildingSelectionMenu());
        exitButton.addActionListener(e -> System.exit(0));

        add(addBuildingButton);
        add(listBuildingsButton);
        add(exitButton);

        revalidate();
        repaint();
    }

    protected void addBuilding() {
        JTextField buildingIdField = new JTextField();
        JTextField buildingNameField = new JTextField();

        Object[] message = {
            "Building ID:", buildingIdField,
            "Building Name:", buildingNameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Building", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String buildingId = buildingIdField.getText().trim();
            String buildingName = buildingNameField.getText().trim();

            if (buildingId.isEmpty() || buildingName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Building ID and Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Building existingBuilding = controller.getBuildingById(buildingId);
            if (existingBuilding != null) {
                JOptionPane.showMessageDialog(this, "Building ID already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Building newBuilding = new Building(buildingId, buildingName);
            controller.addBuilding(newBuilding);
            JOptionPane.showMessageDialog(this, "Building added successfully!");
        }
    }

    protected void showBuildingSelectionMenu() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select a Building:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        DefaultListModel<String> buildingListModel = new DefaultListModel<>();
        List<Building> buildings = controller.getAllBuildings();

        for (Building building : buildings) {
            buildingListModel.addElement(building.getId() + " - " + building.getName());
        }

        JList<String> buildingList = new JList<>(buildingListModel);
        JScrollPane scrollPane = new JScrollPane(buildingList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        JButton selectButton = new JButton("Select");
        JButton backButton = new JButton("Back");

        selectButton.addActionListener(e -> {
            int selectedIndex = buildingList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a building.");
                return;
            }
            currentBuilding = buildings.get(selectedIndex);
            showBuildingMenu();
        });

        backButton.addActionListener(e -> showMainMenu());

        buttonPanel.add(selectButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void showBuildingMenu() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
    
        // Top Panel with Building Details and Legend
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
    
        // Building Details Panel
        JPanel buildingDetailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel buildingNameLabel = new JLabel("Building Name: " + currentBuilding.getName());
        JLabel buildingIdLabel = new JLabel("Building ID: " + currentBuilding.getId());
        JLabel requestedTempLabel = new JLabel("Requested Temperature: " + currentBuilding.getRequestedTemperature() + "°C");
        buildingDetailsPanel.add(buildingNameLabel);
        buildingDetailsPanel.add(buildingIdLabel);
        buildingDetailsPanel.add(requestedTempLabel);
    
        // Legend Panel
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel legendLabel = new JLabel("Legend: ");
        JLabel redLabel = new JLabel("● Heater Enabled");
        redLabel.setForeground(Color.RED);
        JLabel blueLabel = new JLabel("● Cooler Enabled");
        blueLabel.setForeground(Color.BLUE);
        JLabel grayLabel = new JLabel("● Neither");
        grayLabel.setForeground(Color.GRAY);
        legendPanel.add(legendLabel);
        legendPanel.add(redLabel);
        legendPanel.add(blueLabel);
        legendPanel.add(grayLabel);
    
        // Add Panels to Top Panel
        topPanel.add(buildingDetailsPanel);
        topPanel.add(legendPanel);
    
        // Add Top Panel to Main Layout
        add(topPanel, BorderLayout.NORTH);
    
        // Room Details Panel
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
        JScrollPane roomScrollPane = new JScrollPane(roomPanel);
        add(roomScrollPane, BorderLayout.CENTER);
    
        // Populate Room Data Dynamically
        Timer roomUpdateTimer = new Timer(1000, e -> {
            updateRoomPanel(roomPanel);
            requestedTempLabel.setText("Requested Temperature: " + currentBuilding.getRequestedTemperature() + "°C");
        });
        roomUpdateTimer.start();
    
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
    
        JButton addRoomButton = new JButton("Add Room/Apartment");
        JButton adjustTemperatureButton = new JButton("Adjust Building Temperature");
        JButton recalculateButton = new JButton("Recalculate"); // New Button
        JButton returnButton = new JButton("Return to Main Menu");
    
        addRoomButton.addActionListener(e -> addRoom());
        adjustTemperatureButton.addActionListener(e -> {
            adjustTemperature();
            requestedTempLabel.setText("Requested Temperature: " + currentBuilding.getRequestedTemperature() + "°C");
        });
        recalculateButton.addActionListener(e -> {
            recalculateRooms(); // Action for Recalculate
            updateRoomPanel(roomPanel); // Refresh room statuses
        });
        returnButton.addActionListener(e -> {
            currentBuilding = null; // Clear current session
            roomUpdateTimer.stop(); // Stop the timer when returning to the main menu
            showMainMenu();
        });
    
        buttonPanel.add(addRoomButton);
        buttonPanel.add(adjustTemperatureButton);
        buttonPanel.add(recalculateButton);
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.SOUTH);
    
        revalidate();
        repaint();
    }    

    private void recalculateRooms() {
        if (currentBuilding != null) {
            for (Room room : currentBuilding.getRooms()) {
                room.updateHeatingCoolingStates(currentBuilding.getRequestedTemperature());
            }
            JOptionPane.showMessageDialog(this, "Room statuses updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "No building selected. Please select a building first.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    

    private void updateRoomPanel(JPanel roomPanel) {
        roomPanel.removeAll();
    
        for (Room room : currentBuilding.getRooms()) {
            JPanel roomRow = new JPanel();
            roomRow.setLayout(new FlowLayout(FlowLayout.LEFT));
    
            // Room ID and Temperature
            JLabel roomIdLabel = new JLabel("Room ID: " + room.getId());
            JLabel tempLabel = new JLabel("Temperature: " + String.format("%.1f°C", room.getTemperature()));
    
            // Status Icon
            JLabel statusIcon = new JLabel("●");
            if (room.isHeatingEnabled()) {
                statusIcon.setForeground(Color.RED); // Heating enabled
            } else if (room.isCoolingEnabled()) {
                statusIcon.setForeground(Color.BLUE); // Cooling enabled
            } else {
                statusIcon.setForeground(Color.GRAY); // Neither
            }
    
            roomRow.add(roomIdLabel);
            roomRow.add(tempLabel);
            roomRow.add(statusIcon);
    
            // Add Mouse Listener for Details
            roomRow.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    showRoomDetails(room);
                }
            });
    
            roomPanel.add(roomRow);
        }
    
        roomPanel.revalidate();
        roomPanel.repaint();
    }

    private void addRoom() {
        AddRoomDialog dialog = new AddRoomDialog(this);
        if (dialog.isConfirmed()) {
            Room newRoom = dialog.getNewRoom();
            if (currentBuilding != null) {
                currentBuilding.addRoom(newRoom);
                JOptionPane.showMessageDialog(this, "Room added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No building selected. Please select a building first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void adjustTemperature() {
        String newTempStr = JOptionPane.showInputDialog(this, "Enter new temperature for the building:");
        try {
            double newTemp = Double.parseDouble(newTempStr);
            currentBuilding.setRequestedTemperature(newTemp);
            JOptionPane.showMessageDialog(this, "Temperature updated successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }

    private void showRoomDetails(Room room) {
        List<Double> temperatureHistory = room.getTemperatureHistory(); // Retrieve the temperature history
        new RoomDetailWindow(this, room, temperatureHistory); // Use RoomDetailWindow to display details
    }
}
