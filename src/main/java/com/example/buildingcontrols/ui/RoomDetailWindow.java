package com.example.buildingcontrols.ui;

import com.example.buildingcontrols.models.Room;

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
