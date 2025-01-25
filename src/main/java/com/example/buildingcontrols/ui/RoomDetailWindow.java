package com.example.buildingcontrols.ui;

import com.example.buildingcontrols.models.Room;
import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.CommonRoom;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomDetailWindow extends JDialog {
    public RoomDetailWindow(JFrame parent, Room room, List<Double> temperatureHistory) {
        super(parent, "Room Details", true);
        setSize(800, 600);
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

        JTextArea detailsArea = new JTextArea(details.toString());
        detailsArea.setEditable(false);

        // Create chart panel
        ChartPanel chartPanel = createChartPanel(room);

        // Add to split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(detailsArea), chartPanel);
        splitPane.setDividerLocation(200);
        add(splitPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private ChartPanel createChartPanel(Room room) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int time = 1;

        for (Double temp : room.getTemperatureHistory()) {
            dataset.addValue(temp, "Temperature", "Update " + time++);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Temperature History for Room " + room.getId(),
                "Time (Updates)",
                "Temperature (°C)",
                dataset
        );

        return new ChartPanel(chart);
    }
}
