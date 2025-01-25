package com.example.buildingcontrols;

import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.CommonRoom;
import com.example.buildingcontrols.models.CommonRoomType;
import com.example.buildingcontrols.controllers.CLI;
public class Main {
    public static void main(String[] args) {
        // Create and start the CLI application
        CLI cli = new CLI();
        cli.start(); // Launch the command-line interface
    }
}
