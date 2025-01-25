package com.example.buildingcontrols.ui;

import com.example.buildingcontrols.models.Room;
import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.CommonRoom;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomDetailWindow extends JDialog {
    public RoomDetailWindow(JFrame parent, Room room, List<Double> temperatureHistory) {
        super(parent, "Room Details", true);
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        // Room details
        StringBuilder details = new StringBuilder();
        details.append("Room ID: ").append(room.getId()).append("\n");
        details.append("Current Temperature: ").append(String.format("%.1f°C", room.getTemperature())).append("\n");

        // Check the type of the room and include specific details
        if (room instanceof CommonRoom) {
            CommonRoom commonRoom = (CommonRoom) room;
            details.append("Type: Common Room\n");
            details.append("Common Room Type: ").append(commonRoom.getType()).append("\n");
        } else if (room instanceof Apartment) {
            Apartment apartment = (Apartment) room;
            details.append("Type: Apartment\n");
            details.append("Owner Name: ").append(apartment.getOwnerName()).append("\n");
        } else {
            details.append("Type: Standard Room\n");
        }

        // Add historical temperature data
        details.append("\nTemperature History:\n");
        for (int i = 0; i < temperatureHistory.size(); i++) {
            details.append("Reading ").append(i + 1).append(": ").append(String.format("%.1f°C", temperatureHistory.get(i))).append("\n");
        }

        JTextArea detailsArea = new JTextArea(details.toString());
        detailsArea.setEditable(false);
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
