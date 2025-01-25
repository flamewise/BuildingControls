package com.example.buildingcontrols.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    public MainWindow() {
        // Set up the main window
        setTitle("Building Controls Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10)); // One column, variable rows

        // Add buttons for each CLI functionality
        JButton addBuildingButton = new JButton("Add Building");
        JButton listBuildingsButton = new JButton("List Buildings");
        JButton addRoomButton = new JButton("Add Room/Apartment");
        JButton viewBuildingDetailsButton = new JButton("View Building Details");
        JButton adjustTemperatureButton = new JButton("Adjust Building Temperature");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        addBuildingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Add Building functionality triggered.");
            }
        });

        listBuildingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "List Buildings functionality triggered.");
            }
        });

        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Add Room/Apartment functionality triggered.");
            }
        });

        viewBuildingDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "View Building Details functionality triggered.");
            }
        });

        adjustTemperatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Adjust Temperature functionality triggered.");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to the panel
        mainPanel.add(addBuildingButton);
        mainPanel.add(listBuildingsButton);
        mainPanel.add(addRoomButton);
        mainPanel.add(viewBuildingDetailsButton);
        mainPanel.add(adjustTemperatureButton);
        mainPanel.add(exitButton);

        // Add the panel to the frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        // Run the GUI
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
