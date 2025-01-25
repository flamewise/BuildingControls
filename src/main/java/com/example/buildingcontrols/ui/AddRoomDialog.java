package com.example.buildingcontrols.ui;

import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.CommonRoom;
import com.example.buildingcontrols.models.CommonRoomType;
import com.example.buildingcontrols.models.Room;

import javax.swing.*;
import java.awt.*;

public class AddRoomDialog extends JDialog {
    private Room newRoom; // The newly created room
    private boolean confirmed;

    public AddRoomDialog(JFrame parent) {
        super(parent, "Add Room/Apartment", true);
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));
        setLocationRelativeTo(parent);

        // Room ID
        JLabel roomIdLabel = new JLabel("Room ID:");
        JTextField roomIdField = new JTextField();

        // Room Type
        JLabel roomTypeLabel = new JLabel("Room Type:");
        String[] options = {"", "CommonRoom", "Apartment"}; // Add an empty option at the start
        JComboBox<String> roomTypeCombo = new JComboBox<>(options);

        // Additional fields for specific room types
        JLabel commonRoomTypeLabel = new JLabel("Common Room Type:");
        String[] commonRoomTypes = {"", "GYM", "LIBRARY", "LAUNDRY"}; // Add an empty option at the start
        JComboBox<String> commonRoomTypeCombo = new JComboBox<>(commonRoomTypes);
        JLabel ownerLabel = new JLabel("Owner Name:");
        JTextField ownerField = new JTextField();

        // Dynamic visibility and initial value setup based on room type
        roomTypeCombo.addActionListener(e -> {
            boolean isCommonRoom = roomTypeCombo.getSelectedItem().equals("CommonRoom");
            commonRoomTypeLabel.setVisible(isCommonRoom);
            commonRoomTypeCombo.setVisible(isCommonRoom);

            boolean isApartment = roomTypeCombo.getSelectedItem().equals("Apartment");
            ownerLabel.setVisible(isApartment);
            ownerField.setVisible(isApartment);

            // Reset the fields when switching room types
            if (isCommonRoom) {
                commonRoomTypeCombo.setSelectedIndex(0); // Reset Common Room Type dropdown
            }
        });

        // Buttons
        JButton confirmButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(e -> {
            String roomId = roomIdField.getText().trim();
            String roomType = (String) roomTypeCombo.getSelectedItem();

            if (roomId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Room ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roomType == null || roomType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a Room Type.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roomType.equals("CommonRoom")) {
                String commonRoomType = (String) commonRoomTypeCombo.getSelectedItem();
                if (commonRoomType == null || commonRoomType.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select a Common Room Type.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                CommonRoomType selectedType = CommonRoomType.valueOf(commonRoomType);
                newRoom = new CommonRoom(roomId, selectedType);
            } else if (roomType.equals("Apartment")) {
                String ownerName = ownerField.getText().trim();
                if (ownerName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Owner name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                newRoom = new Apartment(roomId, ownerName);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid room type selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        // Add components to the dialog
        add(roomIdLabel);
        add(roomIdField);
        add(roomTypeLabel);
        add(roomTypeCombo);
        add(commonRoomTypeLabel);
        add(commonRoomTypeCombo);
        add(ownerLabel);
        add(ownerField);
        add(confirmButton);
        add(cancelButton);

        // Initially hide specific fields
        commonRoomTypeLabel.setVisible(false);
        commonRoomTypeCombo.setVisible(false);
        ownerLabel.setVisible(false);
        ownerField.setVisible(false);

        setVisible(true);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Room getNewRoom() {
        return newRoom;
    }
}
