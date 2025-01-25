package com.example.buildingcontrols.ui;

import com.example.buildingcontrols.models.Room;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class RoomHistoryChart extends JFrame {

    public RoomHistoryChart(Room room) {
        setTitle("Temperature History - Room " + room.getId());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the dataset
        DefaultCategoryDataset dataset = createDataset(room);

        // Create the chart
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Temperature History for Room " + room.getId(),
                "Time (Updates)",
                "Temperature (°C)",
                dataset
        );

        // Add the chart to the panel
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(Room room) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int time = 1;

        for (Double temp : room.getTemperatureHistory()) {
            dataset.addValue(temp, "Temperature", "Update " + time++);
        }

        return dataset;
    }

    public static void showRoomChart(Room room) {
        SwingUtilities.invokeLater(() -> {
            RoomHistoryChart chart = new RoomHistoryChart(room);
            chart.setVisible(true);
        });
    }
}
